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
                    <el-table-column prop="c_message" label="信息" width="250">
                    </el-table-column>
                    <el-table-column prop="c_uid" label="卖家" width="250">
                    </el-table-column>
                    <el-table-column fixed="right" label="操作"width="200">
                        <template slot-scope="scope">
                            <el-button @click="edit(scope.row)" type="text" size="small">查看</el-button>
                            <el-button type="text" size="small">购买</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-main>
        </el-container>
        <el-dialog title="收货地址" :visible.sync="dialogFormVisible">
      <span>
        状态：{{state}}<br>
        <el-divider></el-divider>
        详细信息：{{details}}<br>
        <el-divider></el-divider>
        交易地址：{{address}}
        <el-divider></el-divider>

      </span>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
            </div>
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
        data() {
            return {
                dialogFormVisible:false,
                username:"",
                details:"",
                address:"",
                state:"",
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
                        c_state:"",
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
                axios.get('http://localhost:8080/commodity/findAllByState/'+0).then(function (resp) {
                    _this.tableData = resp.data
                    console.log(_this.tableData)
                })
            }
        },
        methods:{
            edit(row) {
                const  _this=this
                this.dialogFormVisible=true
                _this.username=row.c_uid
                _this.details=row.c_details
                _this.address=row.c_address
                if(row.c_state==0){
                    _this.state="售卖中"
                }
                if(row.c_state==1){
                    _this.state="已被购买"
                }
                if(row.c_state==-1){
                    _this.state="待审核"
                }
                console.log(row.c_address);
            }
        }
    };
</script>
