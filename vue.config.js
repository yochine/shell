/**
 * 配置参考:
 * https://cli.vuejs.org/zh/config/
 */
const url = 'http://127.0.0.1:9091/shell'   
const CompressionWebpackPlugin = require('compression-webpack-plugin')
const productionGzipExtensions = ['js', 'css']
module.exports = {
  lintOnSave: true,
  productionSourceMap: false,
  chainWebpack: config => {
    // 忽略的打包文件
    // config.externals({
    //   'vue-router': 'VueRouter',
    //   'vuex': 'Vuex',
    //   'axios': 'axios',
    //   'element-ui': 'ELEMENT'
    // })
    const entry = config.entry('app')
    entry
      .add('babel-polyfill')
      .end()
    entry
      .add('classlist-polyfill')
      .end()
  },
  css: {
    extract: { ignoreOrder: true }
  },
  // eslint-disable-next-line
  configureWebpack: (config) => {
    if (process.env.NODE_ENV === 'production') {
      config.mode = 'production';
      config["performance"] = {//打包文件大小配置
        "maxEntrypointSize": 10000000,
        "maxAssetSize": 30000000
      }
      // 仅在生产环境下启用该配置
      // return {
      //   plugins: [
      //     new CompressionWebpackPlugin({
      //       // filename: '[path].gz[query]',
      //       algorithm: 'gzip',
      //       test: new RegExp('\\.(' + productionGzipExtensions.join('|') + ')$'),
      //       threshold: 10240, // 只有大小大于该值的资源会被处理,当前配置为对于超过1k的数据进行处理，不足1k的可能会越压缩越大
      //       minRatio: 0.99, // 只有压缩率小于这个值的资源才会被处理
      //       deleteOriginalAssets: true // 删除原文件
      //     })
      //   ]
      // }
    }
  },
  // 配置转发代理
  devServer: {
    host: '0.0.0.0', //可以远程访问
    disableHostCheck: true,
    port: 8085,
    open: true, //配置自动启动浏览器
    hot: true, //是否热更新
    proxy: {
      '/api': {
        target: url,
        ws: false, // 需要websocket 开启
        changeOrigin: true,// 如果接口跨域，需要进行这个参数配置
        pathRewrite: {
          '^/api': '/shell'
        }
      }
      // 3.5 以后不需要再配置
    }
  }
}
