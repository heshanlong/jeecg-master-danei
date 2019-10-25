Vue.component('collapse', {
  template: `
    <el-collapse v-model="activeName" accordion>
        <el-collapse-item name="queryCondition">
            <slot></slot>
        </el-collapse-item>
    </el-collapse>
  `,
  props: ['show'],
  data () {
    return {
      activeName: ''
    }
  },
  watch: {
    show (value) {
      this.activeName = value ? 'queryCondition' : ''
    }
  }
})
