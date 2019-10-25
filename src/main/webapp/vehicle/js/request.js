
/* 驾驶员信息 */
const DRIVER_LIST = '/fz/driver/page' // 分页查询驾驶员列表
const UPLOAD_DRIVER = axios.defaults.baseURL + '/fz/driver/upload' // 导入驾驶员信息

/* 车辆信息 */
const VEHICLE_LIST = '/fz/vehicle/page' // 分页查询车辆信息
const VEHICLE_TOTAL_LIST = '/fz/vehicle/list' // 查询所有车辆信息
const UPLOAD_CIVIL_VEHICLE = axios.defaults.baseURL + '/fz/vehicle/civilUpload' // 导入民航车辆信息
const UPLOAD_CONSTRUCT_VEHICLE = axios.defaults.baseURL + '/fz/vehicle/construUpload' // 导入施工车辆信息

/* 车辆白名单 */
const WHITELIST_LIST = '/fz/whitelist/page' // 分页查询车辆白名单列表
const INSERT_WHITELIST = '/fz/whitelist/insert' // 添加车辆白名单
const UPDATE_WHITELIST = '/fz/whitelist/updateById' // 更新车辆白名单
const DELETE_WHITELIST = '/fz/whitelist/deleteById' // 删除车辆白名单
const BATCH_DELETE_WHITELIST = '/fz/whitelist/deleteByIds' // 批量删除车辆白名单
const UPLOAD_WHITELIST = axios.defaults.baseURL + '/fz/whitelist/upload' // 导入车辆白名单
const EXPORT_WHITELIST = axios.defaults.baseURL + '/fz/whitelist/export' // 导出车辆白名单

/* 车辆类型 */
const VEHICLE_TYPE_LIST = '/fz/vehicle-type/page' // 分页查询车辆类型
const VEHICLE_TYPE_TOTAL_LIST = '/fz/vehicle-type/list' // 全部车辆类型
const INSERT_VEHICLE_TYPE = '/fz/vehicle-type/insert' // 添加车辆类型
const DELETE_VEHICLE_TYPE = '/fz/vehicle-type/deleteById' // 删除车辆类型

/* 违规类型 */
const VIOLATION_TYPE_LIST = '/fz/violation-type/page' // 分页查询违规类型
const VIOLATION_TYPE_TOTAL_LIST = '/fz/violation-type/list' // 所有违规类型
const INSERT_VIOLATION_TYPE = '/fz/violation-type/insert' // 添加违规类型
const DELETE_VIOLATION_TYPE = '/fz/violation-type/deleteById' // 删除违规类型
const BATCH_DELETE_VIOLATION_TYPE = '/fz/violation-type/deleteByIds' // 批量删除违规类型
const UPDATE_VIOLATION_TYPE = '/fz/violation-type/updateById' // 更新违规类型

/* 位置服务 */
const VEHICLE_LOCATION = '/fz/location/location' // 查询车辆位置
const VEHICLE_PATH = '/fz/location/path' // 查询车辆轨迹

/* 区域管理 */
const FENCE_TOTAL_LIST = '/fz/fence/list' // 所有区域
const INSERT_FENCE = '/fz/fence/insert' // 添加区域
const DELETE_FENCE = '/fz/fence/deleteById' // 删除区域

/* 施工围栏 */
const CONSTRUCT_FENCE_LIST = '/fz/work-approve/page' // 分页查询施工围栏
const BIND_CONSTRUCT_FENCE = '/fz/work-approve/bind' // 分配施工围栏

/* 跨越围栏 */
const CROSS_FENCE_LIST = '/fz/fence-cross/page' // 分页查询跨越围栏
const INSERT_CROSS_FENCE = '/fz/fence-cross/insert' // 添加跨越围栏
const UPDATE_CROSS_FENCE = '/fz/fence-cross/updateById' // 更新跨越围栏
const DELETE_CROSS_FENCE = '/fz/fence-cross/deleteById' // 删除跨越围栏
const BATCH_DELETE_CROSS_FENCE = '/fz/fence-cross/deleteByIds' // 批量删除跨越围栏

/* 限速围栏 */
const SPREED_FENCE_LIST = '/fz/fence-spreed/page' // 分页查询限速围栏
const INSERT_SPREED_FENCE = '/fz/fence-spreed/insert' // 添加限速围栏
const UPDATE_SPREED_FENCE = '/fz/fence-spreed/updateById' // 更新限速围栏
const DELETE_SPREED_FENCE = '/fz/fence-spreed/deleteById' // 删除跨越围栏
const BATCH_DELETE_SPREED_FENCE = '/fz/fence-spreed/deleteByIds' // 批量删除限速围栏

/* 禁停围栏 */
const NOSTOP_FENCE_LIST = '/fz/fence-spreed/page' // 分页查询限速围栏
const INSERT_NOSTOP_FENCE = '/fz/fence-spreed/insert' // 添加限速围栏
const UPDATE_NOSTOP_FENCE = '/fz/fence-spreed/updateById' // 更新限速围栏
const DELETE_NOSTOP_FENCE = '/fz/fence-spreed/deleteById' // 删除跨越围栏
const BATCH_DELETE_NOSTOP_FENCE = '/fz/fence-spreed/deleteByIds' // 批量删除限速围栏

/* 飞行区围栏 */
const FLIGHT_FENCE_TOTAL_LIST = '/fz/fence-flight/list' // 查询所有飞行区围栏
const INSERT_FLIGHT_FENCE = '/fz/fence-flight/insert' // 添加飞行区围栏
const DELETE_FLIGHT_FENCE = '/fz/fence-flight/deleteById' // 删除飞行区围栏

/* 通行区围栏 */
const AUTHORITY_FENCES = '/fz/fence-authority/list' // 查询所有通行区围栏
const INSERT_AUTHORITY_FENCE = '/fz/fence-authority/insert' // 添加通行区围栏
const DELETE_AUTHORITY_FENCE = '/fz/fence-authority/deleteById' // 删除通行区围栏

/* 违规报警 */
const CONSTRUCT_ALARM_LIST = '/fz/alram-construction/page' // 分页查询施工围栏报警
const ACROSS_ALARM_LIST = '/fz/alarm-across/page' // 分页查询跨越围栏报警
const SPEEDLIMIT_ALARM_LIST = '/fz/alarm-speedlimit/page' // 分页查询限速围栏报警
const NOPARK_ALARM_LIST = '/fz/alarm-nopark/page' // 分页查询禁停围栏报警
const PASSAGEAREA_ALARM_LIST = '/fz/alarm-passagearea/page' // 分页查询通行区围栏报警
const VIOLATION_ALARM_LIST = '/fz/alarm-violation/page' // 分页查询车辆违规
const VIOLATION_APPROVAL_LIST = '/fz/violation-approval/page' // 分页查询违规审批
const VIOLATION_APPROVAL_APRON = '/fz/violation-approval/apron' // 机坪审批
const VIOLATION_APPROVAL_ACCESS = '/fz/violation-approval/access' // 准入审批

/* 查询统计 */
const VEHICLE_STATISTICS = '/fz/statistics-vehicle/page' // 分页查询车辆使用统计
const ROAD_STATISTICS = '/fz/location/statistics' // 道路使用统计




/* 驾驶员信息 */
let getDriverList = params => get(DRIVER_LIST, params) // 分页查询驾驶员列表

/* 车辆信息 */
let getVehicleList = params => get(VEHICLE_LIST, params) // 分页查询车辆列表
let getVehicles = params => get(VEHICLE_TOTAL_LIST, params) // 查询所有车辆

/* 车辆白名单 */
let getWhiteList = params => get(WHITELIST_LIST, params) // 分页查询车辆白名单
let insertWhiteList = params => post(INSERT_WHITELIST, params) // 添加车辆白名单
let updateWhiteList = params => put(UPDATE_WHITELIST, params) // 更新车辆白名单
let deleteWhiteList = params => del(DELETE_WHITELIST, params) // 删除车辆白名单
let batchDeleteWhiteList = params => del(BATCH_DELETE_WHITELIST, params) // 批量删除车辆白名单

/* 车辆类型 */
let getVehicleTypeList = params => get(VEHICLE_TYPE_LIST, params) // 分页查询车辆类型
let getVehicleTypes = () => get(VEHICLE_TYPE_TOTAL_LIST) // 查询所有车辆类型
let insertVehicleType = params => post(INSERT_VEHICLE_TYPE, params) // 添加车辆类型
let deleteVehicleType = params => del(DELETE_VEHICLE_TYPE, params) // 删除车辆类型

/* 违规类型 */
let getViolationTypeList = params => get(VIOLATION_TYPE_LIST, params) // 分页查询违规类型
let getViolationTypes = () => get(VIOLATION_TYPE_TOTAL_LIST) // 查询全部违规类型
let insertViolationType = params => post(INSERT_VIOLATION_TYPE, params) // 添加违规类型
let deleteViolationType = params => del(DELETE_VIOLATION_TYPE, params) // 删除违规类型
let batchDeleteViolationType = params => del(BATCH_DELETE_VIOLATION_TYPE, params) // 批量删除违规类型
let updateViolationType = params => put(UPDATE_VIOLATION_TYPE, params) // 更新违规类型

/* 位置服务 */
let getVehicleLocation = params => get(VEHICLE_LOCATION, params) // 查询车辆位置
let getVehiclePath = params => post(VEHICLE_PATH, params) // 查询车辆轨迹

/* 区域管理 */
let getFences = params => get(FENCE_TOTAL_LIST, params) // 查询所有区域
let deleteFence = params => del(DELETE_FENCE, params) // 删除区域
let insertFence = params => post(INSERT_FENCE, params) // 添加区域

/* 施工围栏 */
let getConstructFenceList = params => get(CONSTRUCT_FENCE_LIST, params) // 分页查询施工围栏
let bindConstructFence = params => post(BIND_CONSTRUCT_FENCE, params) // 分配施工围栏

/* 跨越围栏 */
let getCrossFenceList = params => get(CROSS_FENCE_LIST, params) // 分页查询跨越围栏
let insertCrossFence = params => post(INSERT_CROSS_FENCE, params) // 添加跨越围栏
let updateCrossFence = params => put(UPDATE_CROSS_FENCE, params) // 更新跨越围栏
let deleteCrossFence = params => del(DELETE_CROSS_FENCE, params) // 删除跨越围栏
let batchDeleteCrossFence = params => del(BATCH_DELETE_CROSS_FENCE, params) // 批量删除跨越围栏

/* 限速围栏 */
let getSpreedFenceList = params => get(SPREED_FENCE_LIST, params) // 分页查询限速围栏
let insertSpreedFence = params => post(INSERT_SPREED_FENCE, params) // 添加限速围栏
let updateSpreedFence = params => put(UPDATE_SPREED_FENCE, params) // 更新限速围栏
let deleteSpreedFence = params => del(DELETE_SPREED_FENCE, params) // 删除限速围栏
let batchDeleteSpreedFence = params => del(BATCH_DELETE_SPREED_FENCE, params) // 批量删除限速围栏

/* 禁停围栏 */
let getNostopFenceList = params => get(NOSTOP_FENCE_LIST, params) // 分页查询限速围栏
let insertNostopFence = params => post(INSERT_NOSTOP_FENCE, params) // 添加限速围栏
let updateNostopFence = params => put(UPDATE_NOSTOP_FENCE, params) // 更新限速围栏
let deleteNostopFence = params => del(DELETE_NOSTOP_FENCE, params) // 删除限速围栏
let batchDeleteNostopFence = params => del(BATCH_DELETE_NOSTOP_FENCE, params) // 批量删除限速围栏

/* 飞行区围栏 */
let getFlightFences = () => get(FLIGHT_FENCE_TOTAL_LIST) // 查询所有飞行区围栏
let insertFlightFences = params => post(INSERT_FLIGHT_FENCE, params) // 添加飞行区围栏
let deleteFlightFence = params => del(DELETE_FLIGHT_FENCE, params) // 删除飞行区围栏

/* 通行区围栏 */
let getAuthorityFences = () => get(AUTHORITY_FENCES) // 查询所有通行区围栏
let insertAuthorityFences = params => post(INSERT_AUTHORITY_FENCE, params) // 添加通行区围栏
let deleteAuthorityFence = params => del(DELETE_AUTHORITY_FENCE, params) // 删除通行区围栏

/* 违规报警 */
let getConstructAlarm = params => get(CONSTRUCT_ALARM_LIST, params) // 分页查询施工围栏报警
let getAcrossAlarm = params => get(ACROSS_ALARM_LIST, params) // 分页查询跨越围栏报警
let getSpeedLimitAlarm = params => get(SPEEDLIMIT_ALARM_LIST, params) // 分页查询限速围栏报警
let getNoParkAlarm = params => get(NOPARK_ALARM_LIST, params) // 分页查询禁停围栏报警
let getPassageAreaAlarm = params => get(PASSAGEAREA_ALARM_LIST, params) // 分页查询通行区围栏报警
let getViolationAlarm = params => get(VIOLATION_ALARM_LIST, params) // 分页查询车辆违规
let getViolationApproval = params => get(VIOLATION_APPROVAL_LIST, params) // 分页查询违规审批
let violationApprovalApron = params => put(VIOLATION_APPROVAL_APRON, params) // 机坪审批
let violationApprovalAccess = params => put(VIOLATION_APPROVAL_ACCESS, params) // 准入审批

/* 查询统计 */
let getVehicleStatistics = params => get(VEHICLE_STATISTICS, params) // 分页查询车辆使用统计
let getRoadStatistics = params => post(ROAD_STATISTICS, params) // 查询道路使用统计
