<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="120px">
      <el-form-item label="账号" prop="username">
        <el-input v-model="queryParams.username" placeholder="请输入账号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>

      <el-form-item label="姓名" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入姓名" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input v-model="queryParams.age" placeholder="请输入年龄" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="体重" prop="weight">
        <el-input v-model="queryParams.weight" placeholder="请输入体重" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="身高" prop="height">
        <el-input v-model="queryParams.height" placeholder="请输入身高" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['system:boheUser:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['system:boheUser:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['system:boheUser:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['system:boheUser:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="boheUserList" @selection-change="handleSelectionChange">
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column label="用户编号" align="center" prop="id" />
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="性别" align="center" prop="sex"  >
        <template slot-scope="scope">
          {{ scope.row.sex == 1 ? '女':'男' }}
        </template>
      </el-table-column>
      <el-table-column label="年龄" align="center" prop="age" />
      <el-table-column label="体重KG" align="center" prop="weight" />
      <el-table-column label="用户目标" align="center" prop="type" />

      <el-table-column label="身高" align="center" prop="height" />
      <el-table-column label="账号" align="center" prop="username" />

      <el-table-column label="密码" align="center" prop="password" />
      <el-table-column label="积分" align="center" prop="money" />

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['system:boheUser:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['system:boheUser:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改薄荷用户管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="账号" prop="username">
          <el-input v-model="form.username" placeholder="请输入账号" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="form.age" placeholder="请输入年龄" />
        </el-form-item>
        <el-form-item label="体重KG" prop="weight">
          <el-input v-model="form.weight" placeholder="请输入体重KG" />
        </el-form-item>
        <el-form-item label="目标类型" prop="type">
          <!-- <el-input v-model="form.type" placeholder="请输入身高" /> -->
          <el-select v-model="form.type" placeholder="请选择">
            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="身高" prop="height">
          <el-input v-model="form.height" placeholder="请输入身高" />
        </el-form-item>
        <el-form-item label="积分" prop="money">
          <el-input type="number" v-model="form.money" placeholder="" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listBoheUser, getBoheUser, delBoheUser, addBoheUser, updateBoheUser } from "@/api/system/boheUser";

export default {
  name: "BoheUser",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 薄荷用户管理表格数据
      boheUserList: [],
      // 弹出层标题
      title: "",
      //病症类型
      // options: [{
      //     value: '血糖正常',
      //     label: '血糖正常'
      //   }, {
      //     value: '1型糖尿病',
      //     label: '1型糖尿病'
      //   }, {
      //     value: '2型糖尿病',
      //     label: '2型糖尿病'
      //   }, {
      //     value: '妊娠糖尿病',
      //     label: '妊娠糖尿病'
      //   }, {
      //     value: '其他特异性糖尿病',
      //     label: '其他特异性糖尿病'
      //   }],
        				      options: [{
				          value: '减脂',
				          label: '减脂'
				        }, {
				          value: '增肌',
				          label: '增肌'
				        }, {
				          value: '塑形',
				          label: '塑形'
				        }, {
				          value: '控糖',
				          label: '控糖'
				        }, {
				          value: '增重',
				          label: '增重'
				        }],
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        username: null,
        password: null,
        name: null,
        sex: null,
        age: null,
        weight: null,
        height: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询薄荷用户管理列表 */
    getList() {
      this.loading = true;
      listBoheUser(this.queryParams).then(response => {
        this.boheUserList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        username: null,
        password: null,
        name: null,
        sex: null,
        age: null,
        weight: null,
        height: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加薄荷用户管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getBoheUser(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改薄荷用户管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateBoheUser(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBoheUser(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除薄荷用户管理编号为"' + ids + '"的数据项？').then(function () {
        return delBoheUser(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/boheUser/export', {
        ...this.queryParams
      }, `boheUser_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
