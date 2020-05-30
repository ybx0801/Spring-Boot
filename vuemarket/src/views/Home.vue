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
            <el-table-column prop="c_uid" label="卖家" width="140">
            </el-table-column>
            <el-table-column fixed="right" label="操作"width="200">
              <template slot-scope="scope">
                <el-button @click="edit(scope.row)" type="text" size="small">查看</el-button>
                <el-button @click="Buy(scope.row)" type="text" size="small">购买</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-main>
    </el-container>
    <el-dialog title="提示" :visible.sync="inner" width="30%">
      <span>总共需要{{AllPrice}}</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click=cancel()>取 消</el-button>
        <el-button type="primary" @click=BuyFinal()>确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
      <span>请选择购买数量</span><br><br>
      <el-input-number v-model="num" @change="handleChange" :min="1" :max=max label="描述文字"></el-input-number>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click=Buysure()>确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="收货地址" :visible.sync="dialogFormVisible">
      <span>
        卖家：{{username}}<br>
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
        sure:"",
        AllPrice:"",
        price:"",
        max:"",
        num:1,
        inner:false,
        dialogVisible:false,
        dialogFormVisible:false,
        username:"",
        details:"",
        address:"",
        form: {
          c_id:'',
          c_uid: '',
          c_name: '',
          c_message: '',
          c_details: '',
          c_address: '',
          c_quantity: '',
          c_price: '',
          c_reason:'',
          c_buyer: ''
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
            c_state:"",
            c_buyer:""
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
          console.log(_this.$session.get("username"))
          _this.tableData = resp.data
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
      },
      Buy(row){
        const  _this=this
        this.dialogVisible=true
        _this.max=row.c_quantity
        _this.price=row.c_price
        this.form.c_id=row.c_id
        this.form.c_uid=row.c_uid
        this.form.c_name=row.c_name
        this.form.c_message=row.c_message
        this.form.c_details=row.c_details
        this.form.c_address=row.c_address
        this.form.c_quantity=row.c_quantity
        this.form.c_price=row.c_price
        this.form.c_state=1
        this.form.c_buyer=_this.$session.get("username")
      },
      Buysure(){
        this.inner=true
        const  _this=this
        _this.AllPrice=_this.num*_this.price
        _this.sure=_this.num
      },
      cancel(){
        const  _this=this
        this.inner=false;
        _this.num=1
        _this.AllPrice=0;
      },
      BuyFinal(){
        const  _this=this
        axios.post('http://localhost:8080/commodity/updateMarket',this.form).then(function (resp) {
          console.log(resp.data)
          _this.inner=false
          _this.dialogVisible=false
          alert("付款成功，请根据地址信息联系卖家！")
        })
        this.reload()
      },
      handleChange(value) {
        const  _this=this
        _this.num=value
      }
    },
  };
</script>
