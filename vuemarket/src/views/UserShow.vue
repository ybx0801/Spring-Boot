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
                            <el-button @click="edit(scope.row)" type="text" size="small">修改</el-button>
                            <el-button @click="Delete(scope.row)" type="text" size="small">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-main>
        </el-container>
        <el-dialog title="提示" :visible.sync="dialog" width="30%" >
            <span>确认删除这件商品？</span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialog = false">取 消</el-button>
                <el-button type="primary" @click="DELETE()">确 定</el-button>
            </span>
        </el-dialog>
        <el-dialog title="收货地址" :visible.sync="dialogFormVisible">
            <el-form :model="form":ref="form">
                <el-form-item label="物品" :label-width="formLabelWidth">
                    <el-input v-model="form.c_name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="数量" :label-width="formLabelWidth">
                    <el-input v-model="form.c_quantity" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="价格" :label-width="formLabelWidth">
                    <el-input v-model="form.c_price" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="信息" :label-width="formLabelWidth">
                    <el-input v-model="form.c_message" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="详细信息" :label-width="formLabelWidth">
                    <el-input v-model="form.c_details" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="地址" :label-width="formLabelWidth">
                    <el-input v-model="form.c_address" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="addC('form')">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        inject:['reload'],
        data() {
            return {
                dialog:false,
                dialogFormVisible:false,
                username:"",
                details:"",
                address:"",
                form:{
                    c_id:'',
                    c_uid: '',
                    c_name: '',
                    c_message: '',
                    c_details: '',
                    c_address: '',
                    c_quantity: '',
                    c_price: ''
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
                        c_price:""
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
                axios.get('http://localhost:8080/commodity/findAllById/'+_this.$session.get("username")).then(function (resp) {
                    console.log(_this.$session.get("username"))
                    _this.tableData = resp.data
                })
            }
        },
        methods:{
            edit(row) {
                const  _this=this
                this.dialogFormVisible=true
                this.form.c_id=row.c_id
                this.form.c_uid=this.$session.get("username")
                this.form.c_name=row.c_name
                this.form.c_message=row.c_message
                this.form.c_details=row.c_details
                this.form.c_address=row.c_address
                this.form.c_quantity=row.c_quantity
                this.form.c_price=row.c_price
                this.form.c_state=-1
            },
            addC(form){
                const _this=this
                this.dialogFormVisible=false
                axios.post('http://localhost:8080/commodity/updateMarket',this.form).then(function (resp) {
                    console.log(resp.data)
                    alert("修改成功！")
                })
                this.reload()
            },
            Delete(row){
                this.dialog=true
                this.form.c_id=row.c_id
                this.form.c_uid=this.$session.get("username")
                this.form.c_name=row.c_name
                this.form.c_message=row.c_message
                this.form.c_details=row.c_details
                this.form.c_address=row.c_address
                this.form.c_quantity=row.c_quantity
                this.form.c_price=row.c_price
                this.form.c_state=row.c_state
            },
            DELETE(){
                axios.post('http://localhost:8080/commodity/deleteMarket',this.form).then(function (resp) {
                    console.log(resp.data)
                    alert("删除成功！")
                })
                this.reload()
            }
        }
    };
</script>

<style scoped>

</style>