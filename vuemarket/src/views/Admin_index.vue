<template>
    <el-container style="height: 500px; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
            <el-menu router :default-openeds="['0','1']">
                <el-submenu v-for="(item,index) in $router.options.routes":index="index+''" v-if="item.show3" :key="index">
                    <template slot="title"><i class="el-icon-menu"></i>{{item.name}}</template>
                    <el-menu-item v-for="(item3,index3) in item.children":index="item3.path":class="$route.path==item3.path?'is-active':''" :key="index3">{{item3.name}}</el-menu-item>
                </el-submenu>
            </el-menu>
        </el-aside>
        <el-container>
            <el-header style="text-align: right; font-size: 12px">
                <el-dropdown @command="handleCommand">
                    <i class="el-icon-setting" style="margin-right: 15px"></i>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>查看</el-dropdown-item>
                        <el-dropdown-item command="c">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
                <span>{{$session.get("username")}}</span>
            </el-header>
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    export default {
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
                    c_price: ''
                }
            }
        },
        methods:{
            handleCommand(command){
                const _this=this
                if(command=="c"){
                    _this.$session.clear()
                    _this.$router.push({path: "/"})
                }
            },
        }
    }
</script>

<style scoped>

</style>