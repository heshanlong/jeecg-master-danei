document.write('<link rel="stylesheet" href="./css/delConfirm.css" />')
Vue.component('del-confirm', {
  template: `
    <el-dialog title="确认" class="confirmDialog" :close-on-click-modal="false"
               append-to-body top="30vh" :visible.sync="showDelConfirm">
        <p><img src="./image/confirm.gif" class="icon">你确定永久删除该数据吗?</p>
        <p>
            <button class="sure" @click="remove">确定</button>
            <button @click="$emit('update:show', false)">取消</button>
        </p>
        <footer slot="footer"></footer>
    </el-dialog>
  `,
  props: ['show'],
  computed: {
    showDelConfirm: {
      get () {
        return this.show
      },
      set (value) {
        this.$emit('update:show', value)
      }
    }
  },
  methods: {
    remove () {
      this.$emit('update:show', false)
      this.$emit('remove')
    }
  }
})
