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
                        </template>
                    </el-table-column>
                </el-table>
            </el-main>
        </el-container>
        <el-dialog title="收货详情" :visible.sync="dialog" width="30%" >
            {{message}}
            <el-divider></el-divider>
            <el-rate v-model="value" disabled show-score text-color="#ff9900" score-template="{value}"></el-rate>
            <el-divider></el-divider>
            文字评价:{{comment}}
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialog = false">取 消</el-button>
                <el-button type="primary" @click="dialog=false">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        inject:['reload'],
        data() {
            return {
                message:'',
                comment:'',
                value:"",
                input:"",
                state:"",
                dialog:false,
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
                        c_state:'',
                        c_comment:'',
                        c_star:''
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
                axios.get('http://localhost:8080/commodity/findAllByState/'+3).then(function (resp) {
                    console.log(_this.$session.get("username"))
                    _this.tableData = resp.data
                })
            }
        },
        methods:{
            edit(row) {
                const _this=this
                _this.dialog=true
                if(row.c_uid==_this.$session.get("username")){
                    _this.message="售卖成功！\n买家："+row.c_buyer+"\n"
                    _this.value=row.c_star
                    _this.comment=row.c_comment

                }
                if(row.c_buyer==_this.$session.get("username")){
                    _this.message="购买成功！\n卖家："+row.c_uid+"\n"
                    _this.value=row.c_star
                    _this.comment=row.c_comment
                }
            },
        }
    };
</script>

<style scoped>

</style>