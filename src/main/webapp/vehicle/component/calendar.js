document.write('<link rel="stylesheet" href="./css/calendar.css"/>')
Vue.component('calendar', {
  template: `
    <div class="calendar">
        <el-input id="beginTime" class="leftRadius" v-model="beginTime" v-if="show"></el-input>
        <span class="beginIcon glyphicon glyphicon-calendar"></span>
        <span class="split">~</span>
        <el-input id="endTime" v-model="endTime" v-if="show"></el-input>
        <span  class="endIcon rightRadius glyphicon glyphicon-calendar"></span>
    </div>
  `,
  props: {
    beginTime: String,
    endTime: String,
    type: {
      type: String,
      default: 'date'
    }
  },
  data () {
    return {
      show: true
    }
  },
  watch: {
    type (value) {
      this.show = false
      this.$nextTick(() => {
        this.show = true
        this.$nextTick(this.initCalendar)
      })
    }
  },
  mounted() {
    this.initCalendar()
  },
  methods: {
    initCalendar () {
      layui.use('laydate', () => {
        let laydate = layui.laydate
        laydate.render({
          elem: '#beginTime',
          theme: '#52a6ff',
          type: this.type,
          done: value => {
            this.$emit('update:begin-time', value)
          }
        })
        laydate.render({
          elem: '#endTime',
          theme: '#52a6ff',
          type: this.type,
          done: (value) => {
            this.$emit('update:end-time', value)
          }
        })
      })
    }
  }
})
