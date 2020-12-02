package me.zrxjava.common.utils.sm;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECKeyParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import static me.zrxjava.common.utils.sm.Sm2Util.CURVE;


/**
 * 这个工具类的方法，也适用于其他基于BC库的ECC算法
 * @author void
 */
public class BCECUtil extends GmBaseUtil{
    private static final String ALGO_NAME_EC = "EC";
    private static final String PEM_STRING_PUBLIC = "PUBLIC KEY";
    private static final String PEM_STRING_ECPRIVATEKEY = "EC PRIVATE KEY";

    /**
     * 生成ECC密钥对
     *
     * @return ECC密钥对
     */
    public static AsymmetricCipherKeyPair generateKeyPairParameter(ECDomainParameters domainParameters,
                                                                   SecureRandom random) {
        ECKeyGenerationParameters keyGenerationParams = new ECKeyGenerationParameters(domainParameters,
            random);
        ECKeyPairGenerator keyGen = new ECKeyPairGenerator();
        keyGen.init(keyGenerationParams);
        return keyGen.generateKeyPair();
    }

    public static KeyPair generateKeyPair(ECDomainParameters domainParameters, SecureRandom random)
        throws NoSuchProviderException, NoSuchAlgorithmException,
        InvalidAlgorithmParameterException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGO_NAME_EC, BouncyCastleProvider.PROVIDER_NAME);
        ECParameterSpec parameterSpec = new ECParameterSpec(domainParameters.getCurve(), domainParameters.getG(),
            domainParameters.getN(), domainParameters.getH());
        kpg.initialize(parameterSpec, random);
        return kpg.generateKeyPair();
    }

    public static int getCurveLength(ECKeyParameters ecKey) {
        return getCurveLength(ecKey.getParameters());
    }

    public static int getCurveLength(ECDomainParameters domainParams) {
        return (domainParams.getCurve().getFieldSize() + 7) / 8;
    }

    public static byte[] fixToCurveLengthBytes(int curveLength, byte[] src) {
        if (src.length == curveLength) {
            return src;
        }

        byte[] result = new byte[curveLength];
        if (src.length > curveLength) {
            System.arraycopy(src, src.length - result.length, result, 0, result.length);
        } else {
            System.arraycopy(src, 0, result, result.length - src.length, src.length);
        }
        return result;
    }

    public static BCECPublicKey createBCECPublicKey(String xHex, String yHex) throws NoSuchProviderException, NoSuchAlgorithmException {
        BigInteger X= new BigInteger(xHex,16);

        BigInteger Y= new BigInteger(yHex,16);

        Security.addProvider(new BouncyCastleProvider());                 //载入bc库的支持

        ECNamedCurveParameterSpec spec = ECNamedCurveTable.getParameterSpec("sm2p256v1");       //获取bc库内置曲线参数，曲线名称sm2p256v1

        ECDomainParameters ecDomainParameters = new ECDomainParameters(spec.getCurve(),spec.getG(), spec.getN());

        KeyFactory keyFactory= KeyFactory.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);

        ECCurve ecurve = ecDomainParameters.getCurve();          //获取曲线对象

        ECPoint q = ecurve.createPoint(X,Y);        //创建公钥点

        ECParameterSpec ecps = new ECParameterSpec(ecurve,ecDomainParameters.getG(),ecDomainParameters.getN());

        ECPublicKeySpec keySpec = new ECPublicKeySpec(q, ecps);

//        return (BCECPublicKey) keyFactory.generatePublic(keySpec);
        return new BCECPublicKey("BC",keySpec, BouncyCastleProvider.CONFIGURATION);
    }


    private static X9ECParameters x9ECParameters = GMNamedCurves.getByName("sm2p256v1");
    private static ECParameterSpec ecDomainParameters = new ECParameterSpec(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN());

    public static BCECPublicKey encodEcPublicKey(byte[] publickey)
            throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchProviderException {
        KeyFactory factory = KeyFactory.getInstance(ALGO_NAME_EC, BouncyCastleProvider.PROVIDER_NAME);
        ECPoint point = CURVE.decodePoint(publickey);
        ECPublicKeySpec keySpec = new ECPublicKeySpec(point, ecDomainParameters);
        return (BCECPublicKey) factory.generatePublic(keySpec);
    }


    public static ECPrivateKeyParameters createECPrivateKeyParameters(BigInteger d,
                                                                      ECDomainParameters domainParameters) {
        return new ECPrivateKeyParameters(d, domainParameters);
    }

    public static ECPublicKeyParameters createECPublicKeyParameters(BigInteger x, BigInteger y,
                                                                    ECCurve curve, ECDomainParameters domainParameters) {
        return createECPublicKeyParameters(x.toByteArray(), y.toByteArray(), curve, domainParameters);
    }

    public static ECPublicKeyParameters createECPublicKeyParameters(String xHex, String yHex,
                                                                    ECCurve curve, ECDomainParameters domainParameters) {
        return createECPublicKeyParameters(ByteUtils.fromHexString(xHex), ByteUtils.fromHexString(yHex),
            curve, domainParameters);
    }

    public static ECPublicKeyParameters createECPublicKeyParameters(byte[] xBytes, byte[] yBytes,
                                                                    ECCurve curve, ECDomainParameters domainParameters) {
        final byte uncompressedFlag = 0x04;
        int curveLength = getCurveLength(domainParameters);
        xBytes = fixToCurveLengthBytes(curveLength, xBytes);
        yBytes = fixToCurveLengthBytes(curveLength, yBytes);
        byte[] encodedPubKey = new byte[1 + xBytes.length + yBytes.length];
        encodedPubKey[0] = uncompressedFlag;
        System.arraycopy(xBytes, 0, encodedPubKey, 1, xBytes.length);
        System.arraycopy(yBytes, 0, encodedPubKey, 1 + xBytes.length, yBytes.length);
        return new ECPublicKeyParameters(curve.decodePoint(encodedPubKey), domainParameters);
    }

    public static ECPrivateKeyParameters convertPrivateKeyToParameters(BCECPrivateKey ecPriKey) {
        ECParameterSpec parameterSpec = ecPriKey.getParameters();
        ECDomainParameters domainParameters = new ECDomainParameters(parameterSpec.getCurve(), parameterSpec.getG(),
            parameterSpec.getN(), parameterSpec.getH());
        return new ECPrivateKeyParameters(ecPriKey.getD(), domainParameters);
    }

    public static ECPublicKeyParameters convertPublicKeyToParameters(BCECPublicKey ecPubKey) {
        ECParameterSpec parameterSpec = ecPubKey.getParameters();
        ECDomainParameters domainParameters = new ECDomainParameters(parameterSpec.getCurve(), parameterSpec.getG(),
            parameterSpec.getN(), parameterSpec.getH());
        return new ECPublicKeyParameters(ecPubKey.getQ(), domainParameters);
    }

    public static BCECPublicKey createPublicKeyFromSubjectPublicKeyInfo(SubjectPublicKeyInfo subPubInfo) throws NoSuchProviderException,
        NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        return BCECUtil.convertX509ToECPublicKey(subPubInfo.toASN1Primitive().getEncoded(ASN1Encoding.DER));
    }

    /**
     * 将ECC私钥转换为PKCS8标准的字节流
     *
     * @param priKey
     * @param pubKey 可以为空，但是如果为空的话得到的结果OpenSSL可能解析不了
     * @return
     */
    public static byte[] convertECPrivateKeyToPKCS8(ECPrivateKeyParameters priKey,
                                                    ECPublicKeyParameters pubKey) {
        ECDomainParameters domainParams = priKey.getParameters();
        ECParameterSpec spec = new ECParameterSpec(domainParams.getCurve(), domainParams.getG(),
            domainParams.getN(), domainParams.getH());
        BCECPublicKey publicKey = null;
        if (pubKey != null) {
            publicKey = new BCECPublicKey(ALGO_NAME_EC, pubKey, spec,
                BouncyCastleProvider.CONFIGURATION);
        }
        BCECPrivateKey privateKey = new BCECPrivateKey(ALGO_NAME_EC, priKey, publicKey,
            spec, BouncyCastleProvider.CONFIGURATION);
        return privateKey.getEncoded();
    }

    /**
     * 将PKCS8标准的私钥字节流转换为私钥对象
     *
     * @param pkcs8Key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws InvalidKeySpecException
     */
    public static BCECPrivateKey convertPKCS8ToECPrivateKey(byte[] pkcs8Key)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
        PKCS8EncodedKeySpec peks = new PKCS8EncodedKeySpec(pkcs8Key);
        KeyFactory kf = KeyFactory.getInstance(ALGO_NAME_EC, BouncyCastleProvider.PROVIDER_NAME);
        return (BCECPrivateKey) kf.generatePrivate(peks);
    }

    /**
     * 将PKCS8标准的私钥字节流转换为PEM
     *
     * @param encodedKey
     * @return
     * @throws IOException
     */
    public static String convertECPrivateKeyPKCS8ToPEM(byte[] encodedKey) throws IOException {
        return convertEncodedDataToPEM(PEM_STRING_ECPRIVATEKEY, encodedKey);
    }

    /**
     * 将PEM格式的私钥转换为PKCS8标准字节流
     *
     * @param pemString
     * @return
     * @throws IOException
     */
    public static byte[] convertECPrivateKeyPEMToPKCS8(String pemString) throws IOException {
        return convertPEMToEncodedData(pemString);
    }

    /**
     * 将ECC私钥转换为SEC1标准的字节流
     * openssl d2i_ECPrivateKey函数要求的DER编码的私钥也是SEC1标准的，
     * 这个工具函数的主要目的就是为了能生成一个openssl可以直接“识别”的ECC私钥.
     * 相对RSA私钥的PKCS1标准，ECC私钥的标准为SEC1
     *
     * @param priKey
     * @param pubKey
     * @return
     * @throws IOException
     */
    public static byte[] convertECPrivateKeyToSEC1(ECPrivateKeyParameters priKey,
                                                   ECPublicKeyParameters pubKey) throws IOException {
        byte[] pkcs8Bytes = convertECPrivateKeyToPKCS8(priKey, pubKey);
        PrivateKeyInfo pki = PrivateKeyInfo.getInstance(pkcs8Bytes);
        ASN1Encodable encodable = pki.parsePrivateKey();
        ASN1Primitive primitive = encodable.toASN1Primitive();
        byte[] sec1Bytes = primitive.getEncoded();
        return sec1Bytes;
    }


    /**
     * 将ECC公钥对象转换为X509标准的字节流
     *
     * @param pubKey
     * @return
     */
    public static byte[] convertECPublicKeyToX509(ECPublicKeyParameters pubKey) {
        ECDomainParameters domainParams = pubKey.getParameters();
        ECParameterSpec spec = new ECParameterSpec(domainParams.getCurve(), domainParams.getG(),
            domainParams.getN(), domainParams.getH());
        BCECPublicKey publicKey = new BCECPublicKey(ALGO_NAME_EC, pubKey, spec,
            BouncyCastleProvider.CONFIGURATION);
        return publicKey.getEncoded();
    }

    /**
     * 将X509标准的公钥字节流转为公钥对象
     *
     * @param x509Bytes
     * @return
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static BCECPublicKey convertX509ToECPublicKey(byte[] x509Bytes) throws NoSuchProviderException,
        NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec eks = new X509EncodedKeySpec(x509Bytes);
        KeyFactory kf = KeyFactory.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
        return (BCECPublicKey) kf.generatePublic(eks);
    }


    /**
     * 将X509标准的公钥字节流转为PEM
     *
     * @param encodedKey
     * @return
     * @throws IOException
     */
    public static String convertECPublicKeyX509ToPEM(byte[] encodedKey) throws IOException {
        return convertEncodedDataToPEM(PEM_STRING_PUBLIC, encodedKey);
    }

    /**
     * 将PEM格式的公钥转为X509标准的字节流
     *
     * @param pemString
     * @return
     * @throws IOException
     */
    public static byte[] convertECPublicKeyPEMToX509(String pemString) throws IOException {
        return convertPEMToEncodedData(pemString);
    }

    /**
     * copy from BC
     *
     * @param genSpec
     * @return
     */
    public static X9ECParameters getDomainParametersFromGenSpec(ECGenParameterSpec genSpec) {
        return getDomainParametersFromName(genSpec.getName());
    }

    /**
     * copy from BC
     *
     * @param curveName
     * @return
     */
    public static X9ECParameters getDomainParametersFromName(String curveName) {
        X9ECParameters domainParameters;
        try {
            if (curveName.charAt(0) >= '0' && curveName.charAt(0) <= '2') {
                ASN1ObjectIdentifier oidID = new ASN1ObjectIdentifier(curveName);
                domainParameters = ECUtil.getNamedCurveByOid(oidID);
            } else {
                if (curveName.indexOf(' ') > 0) {
                    curveName = curveName.substring(curveName.indexOf(' ') + 1);
                    domainParameters = ECUtil.getNamedCurveByName(curveName);
                } else {
                    domainParameters = ECUtil.getNamedCurveByName(curveName);
                }
            }
        } catch (IllegalArgumentException ex) {
            domainParameters = ECUtil.getNamedCurveByName(curveName);
        }
        return domainParameters;
    }



    private static String convertEncodedDataToPEM(String type, byte[] encodedData) throws IOException {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        PemWriter pWrt = new PemWriter(new OutputStreamWriter(bOut));
        try {
            PemObject pemObj = new PemObject(type, encodedData);
            pWrt.writeObject(pemObj);
        } finally {
            pWrt.close();
        }
        return new String(bOut.toByteArray());
    }

    private static byte[] convertPEMToEncodedData(String pemString) throws IOException {
        ByteArrayInputStream bIn = new ByteArrayInputStream(pemString.getBytes());
        PemReader pRdr = new PemReader(new InputStreamReader(bIn));
        try {
            PemObject pemObject = pRdr.readPemObject();
            return pemObject.getContent();
        } finally {
            pRdr.close();
        }
    }
}
