document.write('<link rel="stylesheet" href="./css/fenceDetail.css"/>')
document.write('<script src="./component/vehicleSelect.js"></script>')
document.write('<script src="./component/vehicleTypeSelect.js"></script>')
Vue.component('fence-detail', {
  template: `
    <div class="fenceDetail">
      <el-form :model="fenceDetail" :rules="rules" label-width="85px" ref="fenceDetailForm" inline>
        <el-form-item label="区域选择：" prop="fenceId">
          <el-select v-model="fenceDetail.fenceId" class="block">
            <el-option v-for="item in fenceList" :key="item.id" :label="item.fenceName" :value="item.id"></el-option>
          </el-select>
          <button @click.prevent="$emit('manage')" class="normal mlBtn">管理</button>
        </el-form-item>
        <el-form-item label="围栏名称：" prop="projectName">
          <el-input v-model="fenceDetail.projectName" class="block"></el-input>
        </el-form-item>
        <slot></slot>
        <el-form-item label="开始日期：" prop="beginTime">
          <el-input v-model="fenceDetail.beginTime" class="inline" id="beginTime"></el-input>
        </el-form-item>
        <el-form-item label="结束日期：" prop="endTime">
          <el-input v-model="fenceDetail.endTime" class="inline" id="endTime"></el-input>
        </el-form-item>
        <el-form-item label="时间范围：" required>
          <el-form-item prop="beginRange" style="margin-right: 0">
            <el-input v-model="fenceDetail.beginRange" class="inline" id="beginRange"></el-input>
          </el-form-item>
          <span class="split">至</span>
          <el-form-item prop="endRange">
            <el-input v-model="fenceDetail.endRange" class="inline" id="endRange"></el-input>
          </el-form-item>
        </el-form-item>
        <el-form-item label="围栏分配：">
          <button  @click.prevent="showVehicle=true" class="normal">车牌号</button>
          <button  @click.prevent="showVehicleType=true" class="normal mlBtn">车辆类型</button>
        </el-form-item>
        <el-form-item label="审批单：">
          <el-select v-model="fenceDetail.approval" class="inline">
            <el-option label="开启" :value="0"></el-option>
            <el-option label="关闭" :value="1"></el-option>            
          </el-select>
        </el-form-item>
      </el-form>
      <p><button class="sure" @click="saveFence">确定</button></p>
      <el-dialog title="车辆选择" :visible.sync="showVehicle" class="vehicleDialog" append-to-body @closed="$refs.vehicleSel.reset()">
        <vehicle-select ref="vehicleSel" :sel-vehicles="selVehicles" @select-finish="selectFinish($event, 0)"></vehicle-select>
      </el-dialog>
      <el-dialog title="车辆类型选择" :visible.sync="showVehicleType" class="vehicleTypeDialog" append-to-body @closed="$refs.vehicleTypeSel.reset()">
        <vehicle-type-select ref="vehicleTypeSel" :sel-vehicle-types="selVehicleTypes" @select-finish="selectFinish($event, 1)"></vehicle-type-select>
      </el-dialog>
    </div>
  `,
  props: {
    fenceDetail: {
      type: Object,
      default: () => {}
    }
  },
  data () {
    return {
      fenceList: [],
      showVehicle: false,
      showVehicleType: false,
      selVehicles: [],
      selVehicleTypes: [],
      vehicleList: [],
      rules: {
        fenceId: {required: true, message: '请选择区域', trigger: 'blur'},
        projectName: {required: true, message: '请输入围栏名称', trigger: 'blur'},
        beginTime: {required: true, message: '请选择开始日期'},
        endTime: {required: true, message: '请选择结束日期'},
        beginRange: {required: true, message: '请选择开始时间'},
        endRange: {required: true, message: '请选择结束时间'},
        crossType: {required: true, message: '请选择类型', trigger: 'blur'},
        openState: {required: true, message: '请选择开启状态', trigger: 'blur'},
        spreed: {required: true, message: '请输入最高限速', trigger: 'blur'}
      }
    }
  },
  watch: {
    fenceDetail () {
      this.initSelect()
    }
  },
  mounted () {
    getFences().then(res => {this.fenceList = res.data})
    layui.use('laydate', () => {
      this.initDate('beginTime', 'beginTime')
      this.initDate('endTime', 'endTime')
      this.initDate('beginRange', 'beginRange', 'time')
      this.initDate('endRange', 'endRange', 'time')
    })
    getVehicles().then(res => {
      this.vehicleList = res.data
      this.initSelect()
    })
  },
  methods: {
    initDate (id, key, type = 'date') {
      let laydate = layui.laydate
      laydate.render({
        elem: '#' + id,
        theme: '#52a6ff',
        type,
        done: value => {
          this.fenceDetail[key] = value
        }
      })
    },
    clearValidate () {
      this.$refs.fenceDetailForm.clearValidate()
    },
    initSelect () {
      this.selVehicleTypes = []
      if (this.fenceDetail.itemList) {
        this.fenceDetail.itemList.map(async id => {
          let type = await getVehicleTypeById(id)
          this.selVehicleTypes.push(type)
        })
      }
      this.selVehicles = []
      if (this.fenceDetail.platesList) {
        this.fenceDetail.platesList.forEach(plate => {
          let vehicle = this.vehicleList.find(v => v.licensePlate === plate)
          if (vehicle) this.selVehicles.push(vehicle)
        })
      }
    },
    selectFinish (value, b) {
      b ? this.selVehicleTypes = value : this.selVehicles = value
      b ? this.showVehicleType = false : this.showVehicle = false
    },
    saveFence () {
      this.$refs.fenceDetailForm.validate(valid => {
        if (!valid) return
        this.fenceDetail.licensePlate = this.selVehicles.map(item => item.licensePlate)
        this.fenceDetail.itemIdArray = this.selVehicleTypes.map(item => item.id)
        this.$emit('save')
      })
    }
  }
})
