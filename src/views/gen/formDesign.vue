<template> 
 <div id='app'> 
   <avue-form-design 
     style='height: 100vh;' 
     :options='options' 
     :aside-left-width='275' 
     :aside-right-width='360' 
     storage @submit='handleSubmit'> 
    </avue-form-design> 
 </div>
</template>

<script>

export default { 
    data () {
      return {
          options: { // 可以是Object
            column: [{
                label: '单行文本',
                prop: 'input',
                type: 'input'
            }]
          }
      } 
    }, 
    methods: { 
        // 生成json回调 
        handleSubmit(data) {
         console.log(data)
        } 
    }
}

</script>