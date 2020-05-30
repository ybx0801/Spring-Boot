<template>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="username">
            <el-input v-model="ruleForm.username" style="width: 10%"></el-input>
        </el-form-item>
        <el-form-item label="用户密码" prop="userpwd">
            <el-input v-model="ruleForm.userpwd" style="width: 10%"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')" >注册</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
    export default {
        data() {
            return {
                item:[
                    {id: 0, idView: require('../assets/logo.png')},
                    {id: 1, idView: require('../assets/1.jpg')}
                ],
                ruleForm: {
                    username: '',
                    userpwd: ''
                },
                rules: {
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' },
                        { min: 3, max: 7, message: '长度在5到7个字符以内', trigger: 'blur' }
                    ],
                    userpwd: [
                        { required: true, message: '请输入密码', trigger: 'blur' },
                        { min: 3, max: 7, message: '长度在5到7个字符以内', trigger: 'blur' }
                    ]
                }
            };
        },
        methods:{
            submitForm(formName) {
                const _this=this
                const userpwd=_this.ruleForm.userpwd
                const username=_this.ruleForm.username
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.post("http://localhost:8080/user_market/addUser",this.ruleForm).then(function (resp) {
                            if(resp.data=="1"){
                               alert("注册成功")
                                _this.ruleForm.username=null
                                _this.ruleForm.userpwd=null
                            }
                            else if(resp.data=="-1"){
                                alert("用户名已被注册")
                                _this.ruleForm.username=null
                                _this.ruleForm.userpwd=null
                            }

                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
        }
    }
</script>

<style scoped>

</style>