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
                    <el-table-column prop="c_uid" label="卖家" width="140">
                    </el-table-column>
                    <el-table-column prop="c_details" label="详细信息" width="140">
                    </el-table-column>
                    <el-table-column prop="c_address" label="交易地址" width="140">
                    </el-table-column>
                    <el-table-column fixed="right" label="操作"width="200">
                        <template slot-scope="scope">
                            <el-button @click="edit(scope.row)" type="text" size="small">通过</el-button>
                            <el-button @click="pass(scope.row)" type="text" size="small">打回</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-main>
        </el-container>
        <el-dialog title="提示" :visible.sync="dialog" width="30%" >
            <el-input v-model="input" placeholder="请输入内容"></el-input>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialog = false">取 消</el-button>
                <el-button type="primary" @click="p()">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<style>
    .el-header {
        background-color: #B3C0D1;
        color: #333;
        line-height: 60px;
    }

    .el-aside {
        color: #333;
    }
</style>

<script>
    export default {
        inject:['reload'],
        data() {
            return {
                input:"",
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
                    c_reason:''
                },
                formLabelWidth: '120px',
                tableData:[
                    {
                        c_id:"",
                        c_name:"",
                        c_message:"",
                        c_details:"",
                        c_address:"",
                        c_quantity:"",
                        c_price:"",
                    }
                ],
                Details:[
                    {
                        username:this.username,
                        c_address:"",
                        c_details:"",
                    }
                ]
            }
        },
        created() {
            const _this=this
            if(_this.$session.get("username")==null){
                _this.$router.push("/")
                alert("请先登录！")
            }else {
                axios.get('http://localhost:8080/commodity/findAllByState/'+-1).then(function (resp) {
                    console.log(_this.$session.get("username"))
                    _this.tableData = resp.data
                })
            }
        },
        methods:{
            edit(row) {
                const  _this=this
                this.form.c_id=row.c_id
                this.form.c_uid=this.$session.get("username")
                this.form.c_name=row.c_name
                this.form.c_message=row.c_message
                this.form.c_details=row.c_details
                this.form.c_address=row.c_address
                this.form.c_quantity=row.c_quantity
                this.form.c_price=row.c_price
                this.form.c_state=0
                axios.post('http://localhost:8080/commodity/updateMarket',this.form).then(function (resp) {
                    _this.tableData = resp.data
                    alert("该商品已通过审核！")
                })
                this.reload()
            },
            pass(row){
                this.dialog=true
                this.form.c_id=row.c_id
                this.form.c_uid=row.c_uid
                this.form.c_name=row.c_name
                this.form.c_message=row.c_message
                this.form.c_details=row.c_details
                this.form.c_address=row.c_address
                this.form.c_quantity=row.c_quantity
                this.form.c_price=row.c_price
                this.form.c_state=row.c_state
            },
            p(){
                this.form.c_reason=this.input
                axios.post('http://localhost:8080/commodity/updateMarket',this.form).then(function (resp) {
                    _this.tableData = resp.data
                    alert("该商品已打回！")
                })
                this.reload()
            }
        }
    };
</script>
