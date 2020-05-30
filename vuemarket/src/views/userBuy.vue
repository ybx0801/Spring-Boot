<template>
    <div>
        <el-container style="height: 500px; border: 1px solid #eee">
            <el-main>
                <el-table :data="tableData">
                    <el-table-column prop="index" label="序号" width="140">
                        <template slot-scope="scope">
                            <span>{{scope.$index+1}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column prop="c_name" label="物品" width="140">
                    </el-table-column>
                    <el-table-column prop="c_quantity" label="数量"width="140">
                    </el-table-column>
                    <el-table-column prop="c_price" label="价格"width="140">
                    </el-table-column>
                    <el-table-column prop="c_message" label="信息" width="140">
                    </el-table-column>
                    <el-table-column prop="c_details" label="详细信息" width="140">
                    </el-table-column>
                    <el-table-column prop="c_address" label="交易地址" width="140">
                    </el-table-column>
                    <el-table-column fixed="right" label="操作"width="200">
                        <template slot-scope="scope">
                            <el-button @click="edit(scope.row)" type="text" size="small">查看</el-button>
                            <el-button @click="assess(scope.row)" type="text" size="small">评价</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-main>
        </el-container>
        <el-dialog title="收货详情" :visible.sync="dialog1" width="30%" >
            <span>请输入评价内容：</span><br><br>
            <el-rate v-model="value" show-text></el-rate><br>
            <el-input v-model="input" placeholder="请输入内容"></el-input>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialog1 = false">取 消</el-button>
                <el-button type="primary" @click="ass()">评价</el-button>
            </span>
        </el-dialog>
        <el-dialog title="收货详情" :visible.sync="dialogFormVisible">
           卖家：{{form.c_uid}}
            <el-divider></el-divider>
            状态：{{state}}
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="Sure()">确认收货</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        inject:['reload'],
        data() {
            return {
                star:"",
                comment:"",
                value:"",
                dialog1:false,
                input:"",
                state:"",
                dialog:false,
                dialogFormVisible:false,
                form:{
                    c_id:'',
                    c_uid: '',
                    c_name: '',
                    c_message: '',
                    c_details: '',
                    c_address: '',
                    c_quantity: '',
                    c_price: '',
                    c_comment:'',
                    c_star:'',
                    c_state:''
                },
                formLabelWidth: '90px',
                tableData:[
                    {
                        c_id:"",
                        c_uid:"",
                        c_name:"",
                        c_message:"",
                        c_details:"",
                        c_address:"",
                        c_quantity:"",
                        c_price:"",
                        c_state:''
                    }
                ],
            }
        },
        created() {
            const _this=this
            if(_this.$session.get("username")==null){
                _this.$router.push("/")
                alert("请先登录！")
            }else {
                axios.get('http://localhost:8080/commodity/findAllByBuyer/'+_this.$session.get("username")+"/"+1).then(function (resp) {
                    console.log(_this.$session.get("username"))
                    _this.tableData = resp.data
                })
            }
        },
        methods:{
            edit(row) {
                const  _this=this
                this.form.c_id=row.c_id
                this.form.c_uid=row.c_uid
                this.form.c_name=row.c_name
                this.form.c_message=row.c_message
                this.form.c_details=row.c_details
                this.form.c_address=row.c_address
                this.form.c_quantity=row.c_quantity
                this.form.c_price=row.c_price
                this.form.c_state=row.c_state
                this.form.c_buyer=row.c_buyer
                if(this.form.c_state==1){
                    _this.dialogFormVisible=true
                    _this.state="待收货"
                }

            },
            Sure(){
                this.form.c_state=1
                axios.post('http://localhost:8080/commodity/updateMarket',this.form).then(function (resp) {
                    console.log(resp.data)
                    alert("收货成功，请及时评价！")
                })
                this.reload()
            },
            assess(row){
                this.dialog1=true
                this.form.c_id=row.c_id
                this.form.c_uid=row.c_uid
                this.form.c_name=row.c_name
                this.form.c_message=row.c_message
                this.form.c_details=row.c_details
                this.form.c_address=row.c_address
                this.form.c_quantity=row.c_quantity
                this.form.c_price=row.c_price
                this.form.c_state=3
                this.form.c_buyer=row.c_buyer
            },
            ass(){
                const  _this=this
                this.form.c_comment=_this.input
                this.form.c_star=_this.value
                axios.post('http://localhost:8080/commodity/updateMarket',this.form).then(function (resp) {
                    console.log(resp.data)
                    alert("评价成功")
                })
                this.reload()
            }
        }
    };
</script>

<style scoped>

</style>