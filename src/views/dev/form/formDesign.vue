<template> 
  <div class="webapp">
    <basic-container>
      <avue-form-design 
      :options="option" 
      @submit="handleSubmit">
      </avue-form-design>
    </basic-container>
  </div>
</template>

<script>
import { getForm, postForm } from "@/api/dev/form"
import { validatenull } from "@/util/validate";


export default { 
    data () {
      return {
          option: { // 可以是Object
            column: [{
                label: '单行文本',
                prop: 'input',
                type: 'input'
            }]
          }
      } 
    }, 
    created(){this.getFormInfo()},
    methods: { 
        // 生成json回调 
      handleSubmit(json) {
        let params = this.$route.query;
        if (validatenull(params)) {
           return false;
        }
        let result = JSON.stringify(json);
        postForm(result, params.tableId, params.dsName).then((response) => {
          this.$message.success("生成并保存成功");
        });ß
      },
      getFormInfo() {
        let params = this.$route.query;
        if (validatenull(params)) {
          return false;
        }
        getForm(params.tableId, params.dsName).then((response) => {
          if (!validatenull(response.data.data)) {
            this.option = JSON.parse(response.data.data);
          }
        });
      },
    }
}

</script>
<style lang="scss">
.webapp {
  background-color: #fff;
  position: relative;
  width: 100%;
  height: 100%;

  .form-designer {
    height: 800px;
    overflow-y: scroll;
  }

  .form-designer .widget-config-container .el-tabs__header {
    position: relative;
  }
}
</style>