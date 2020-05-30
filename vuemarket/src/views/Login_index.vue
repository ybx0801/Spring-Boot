<template>
    <div>
        <el-carousel :interval="4000" type="card" height="220px">
            <el-carousel-item v-for="item in item" :key="item.id">
                <img ref="imgHeight":src="item.idView" class="image">
            </el-carousel-item>
        </el-carousel>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="用户名" prop="username">
                <el-input v-model="ruleForm.username" style="width: 10%"></el-input>
            </el-form-item>
            <el-form-item label="用户密码" prop="userpwd">
                <el-input v-model="ruleForm.userpwd" style="width: 10%"></el-input>
            </el-form-item>
            <el-form-item>
                <el-radio v-model="radio" label="0">用户</el-radio>
                <el-radio v-model="radio" label="1">管理员</el-radio>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')" >登录</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {

        data() {
            return {
                radio:"",
                item:[
                    {id: 0, idView: require('../assets/logo.png')},
                    {id: 1, idView: require('../assets/实验3.jpg')},
                    {id: 2, idView: require('../assets/2.jpg')},
                    {id: 3, idView: require('../assets/timg.jpg')}
                ],
                ruleForm: {
                    username: '',
                    userpwd: ''
                },
                rules: {
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' },
                        { min: 3, max: 7, message: '长度在3到7个字符以内', trigger: 'blur' }
                    ],
                    userpwd: [
                        { required: true, message: '请输入密码', trigger: 'blur' },
                        { min: 3, max: 7, message: '长度在3到7个字符以内', trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
            test(){
                console.log(this.ruleForm.username)
            },
            submitForm(formName) {
                const _this = this
                if (_this.radio==0){
                    const userpwd = _this.ruleForm.userpwd
                    const username = _this.ruleForm.username
                    this.$refs[formName].validate((valid) => {
                        if (valid) {
                            axios.get("http://localhost:8080/user_market/findUser/" + username + "/" + userpwd).then(function (resp) {
                                if (resp.data == "1") {
                                    _this.$session.set("username", username)
                                    _this.$router.push({path: "/Home"})
                                } else if (resp.data == "0") {
                                    alert("密码错误！")
                                } else if (resp.data == "-1") {
                                    alert("用户不存在！")
                                }

                         })
                        } else {
                            console.log('error submit!!');
                            return false;
                        }
                    });
                }
                if(_this.radio==1){
                    const userpwd = _this.ruleForm.userpwd
                    const username = _this.ruleForm.username
                    this.$refs[formName].validate((valid) => {
                        if (valid) {
                            axios.get("http://localhost:8080/Admin/findAdmin/" + username + "/" + userpwd).then(function (resp) {
                                if (resp.data == "1") {
                                    _this.$session.set("username", username)
                                    _this.$router.push({path: "/Admin"})
                                } else if (resp.data == "0") {
                                    alert("密码错误！")
                                } else if (resp.data == "-1") {
                                    alert("用户不存在！")
                                }

                            })
                        } else {
                            console.log('error submit!!');
                            return false;
                        }
                    });
                }
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            create(){

            }
        }
    }
</script>

<style>
    .el-carousel__item h3 {
        color: #475669;
        font-size: 15px;
        opacity: 0.75;
        line-height: 200px;
        margin: 0;
    }

    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n+1) {
        background-color: #d3dce6;
    }
</style>