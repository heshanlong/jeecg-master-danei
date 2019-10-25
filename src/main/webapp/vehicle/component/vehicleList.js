document.write('<link rel="stylesheet" href="./css/vehicleList.css"/>')
Vue.component('vehicle-list', {
  template: `
    <div class="vehicleList" :style="{height}">
      <button @click="height='40px'" class="close" v-if="height===maxHeight"><i class="glyphicon glyphicon-remove"></i></button>
      <el-tabs @tab-click="maximize" v-model="activeName">
        <el-tab-pane label="车辆列表" name="vehicleList">
          <table-list @selected-change="selectedItems=$event" showPagination
                    :data-source="dataSource" :show-select="true" :header-list="headerList">
          </table-list>
          <p><button class="normal" @click="selectFinish">确定</button></p>
        </el-tab-pane>
        <slot></slot>
      </el-tabs>
    </div>
  `,
  props: {
    dataSource: Array,
    maxHeight: {
      type: String,
      default: '300px'
    }
  },
  data () {
    return {
      headerList: [
        {label: '车牌号', prop: 'licensePlate', width: 80, show: true},
        {label: '机动车厂牌', prop: 'brand', width: 95, show: true },
        {label: '机动车型号', prop: 'model', width: 95, show: true },
        {label: '车身颜色', prop: 'color', width: 85, show: true },
        {label: '所属/申办单位', prop: 'company', width: 90, show: true },
        {label: '申请/施工单位', prop: 'applyCompany', width: 90, show: true },
        {label: '车辆类型', prop: 'itemName', width: 80, show: true },
        {label: '证件类别', prop: 'idType', func: d => d ? '施工车辆' : '民航车辆', width: 85, show: true },
        {label: '证件有效期开始', prop: 'beginTime', width: 85, show: true },
        {label: '证件有效期结束', prop: 'endTime', width: 85, show: true },
        {label: '通行道口', prop: 'passageAuthority', width: 80, show: true },
        {label: '通行区域', prop: 'passageRegion', width: 90, show: true },
        {label: '通行证号码', prop: 'cardNo', width: 100, show: true },
        {label: '施工项目', prop: 'project', width: 90, show: true },
        {label: '施工区域', prop: 'area', width: 90, show: true },
        {label: '引领车牌号', prop: 'leadCar', width: 95, show: true },
        {label: '引领人工作号', prop: 'leadPerson', width: 105, show: true }
      ],
      activeName: 'vehicleList',
      selectedItems: [],
      height: '40px'
    }
  },
  methods: {
    maximize () {
      this.height = this.maxHeight
    },
    selectFinish () {
      if (!this.selectedItems.length) {
        warning('请选择车辆')
        return
      }
      let selectVehicles = this.selectedItems.map(index => this.dataSource[index])
      this.$emit('selected', selectVehicles)
    },
    changePane (name) {
      this.activeName = name
    }
  }
})
