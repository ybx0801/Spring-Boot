<template>
    <div>
        <h1>宇宙最大二手交易市场</h1>
        <el-container style="height: 500px; border: 1px solid #eee">
            <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
                <el-menu router :default-openeds="['0','1']">
                    <el-submenu v-for="(item,index) in $router.options.routes":index="index+''" v-if="item.show2" :key="index">
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
                            <el-dropdown-item>退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                    <span>请登录</span>
                </el-header>
                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                dialogFormVisible:false,
                formLabelWidth: '90px',
                form:{
                    c_id:'',
                    c_uid: '',
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
            },
        }
    }
</script>
