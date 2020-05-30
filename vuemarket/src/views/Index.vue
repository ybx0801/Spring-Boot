<template>
    <el-container style="height: 500px; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
            <el-menu router :default-openeds="['0','1']">
                <el-submenu v-for="(item,index) in $router.options.routes":index="index+''" v-if="item.show" :key="index">
                    <template slot="title"><i class="el-icon-menu"></i>{{item.name}}</template>
                    <el-menu-item v-for="(item2,index2) in item.children":index="item2.path":class="$route.path==item2.path?'is-active':''" :key="index2">{{item2.name}}</el-menu-item>
                </el-submenu>
            </el-menu>
        </el-aside>
        <el-container>
            <el-header style="text-align: right; font-size: 12px">
                <el-dropdown @command="handleCommand">
                    <i class="el-icon-setting" style="margin-right: 15px"></i>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>查看</el-dropdown-item>
                        <el-dropdown-item command="b">新增</el-dropdown-item>
                        <el-dropdown-item command="c">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
                <span>{{$session.get("username")}}</span>
            </el-header>
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
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    export default {
        name: "Index",
        inject:['reload'],
        data(){
            return{
                dialogFormVisible:false,
                formLabelWidth: '90px',
                form:{
                    c_id:'',
                    c_uid: this.$session.get("username"),
                    c_name: '',
                    c_message: '',
                    c_details: '',
                    c_address: '',
                    c_quantity: '',
                    c_price: '',
                    c_state: -1
                }
            }
        },
        methods:{
            handleCommand(command){
                const _this=this
                if(command=="b"){
                    this.dialogFormVisible=true

                }
                if(command=="c"){
                    _this.$session.clear()
                    _this.$router.push({path: "/"})
                }
            },
            addC(form){
                const _this=this
                this.dialogFormVisible=false
                axios.post('http://localhost:8080/commodity/addMarket',this.form).then(function (resp) {
                    console.log(resp.data)
                })
                this.reload()
            }
        }
    }
</script>

<style scoped>

</style>