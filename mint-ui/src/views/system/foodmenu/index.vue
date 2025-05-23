<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="120px">
      <el-form-item label="食谱" prop="title">
        <el-input v-model="queryParams.title" placeholder="请输入食谱" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['system:foodmenu:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['system:foodmenu:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['system:foodmenu:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['system:foodmenu:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="foodmenuList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="食谱编号" align="center" prop="id" />
      <el-table-column label="食谱" align="center" prop="title" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['system:foodmenu:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['system:foodmenu:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改推荐食谱对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="食谱" prop="title">
          <el-input v-model="form.title" placeholder="请输入食谱" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-divider content-position="center">推荐食物详情信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddBhMenuInfo">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteBhMenuInfo">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="bhMenuInfoList" :row-class-name="rowBhMenuInfoIndex"
          @selection-change="handleBhMenuInfoSelectionChange" ref="bhMenuInfo">
          <el-table-column type="selection" width="50" align="center" />
          <!-- <el-table-column label="序号" align="center" prop="index" width="50" /> -->
          <el-table-column label="食物编号" prop="foodId" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.foodId" filterable remote reserve-keyword :remote-method="remoteMethod"
                :loading="loading" placeholder="请选择食物">
                <el-option v-for="item in foodList" :key="item.id" :label="item.name" :value="item.id">
                </el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="食物名称" prop="food.name" width="150">
          </el-table-column>
          <el-table-column label="餐食时间" prop="type" >
            <template slot-scope="scope">
              <el-select v-model="scope.row.type" placeholder="请选择餐食时间">
                <el-option v-for="dict in dateList" :key="dict.type" :label="dict.name"
                  :value="dict.type"></el-option>
              </el-select>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {
    listFoodmenu,
    getFoodmenu,
    delFoodmenu,
    addFoodmenu,
    updateFoodmenu
  } from "@/api/system/foodmenu";
  import {
    listFood,
    getFood,
    delFood,
    addFood,
    updateFood
  } from "@/api/system/food";

  export default {
    name: "Foodmenu",
    dicts: ['sys_user_sex'],
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 子表选中数据
        checkedBhMenuInfo: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 推荐食谱表格数据
        foodmenuList: [],
        // 推荐食物详情表格数据
        bhMenuInfoList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          title: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {},
        foodList: [],
        dateList: [{
            name: "早餐",
            type: 0
          },
          {
            name: "午餐",
            type: 1
          }, {
            name: "晚餐",
            type: 2
          },

        ]
      };
    },
    created() {
      this.getList();
    },
    methods: {
      //搜索食物
      remoteMethod(query) {
        if (query !== '') {
          this.loading = true;

          listFood({
            pageNum: 1,
            pageSize: 100,
            name: query
          }).then((res) => {
            console.log(res)
            this.foodList = res.rows
            this.loading = false;

          })



        } else {
          this.options = [];
        }
      },
      /** 查询推荐食谱列表 */
      getList() {
        this.loading = true;
        listFoodmenu(this.queryParams).then(response => {
          this.foodmenuList = response.rows;
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
          title: null,
          createTime: null,
          remark: null
        };
        this.bhMenuInfoList = [];
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
        this.title = "添加推荐食谱";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getFoodmenu(id).then(response => {
          this.form = response.data;
          this.bhMenuInfoList = response.data.bhMenuInfoList;
          this.open = true;
          this.title = "修改推荐食谱";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            this.form.bhMenuInfoList = this.bhMenuInfoList;
            if (this.form.id != null) {
              updateFoodmenu(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addFoodmenu(this.form).then(response => {
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
        this.$modal.confirm('是否确认删除推荐食谱编号为"' + ids + '"的数据项？').then(function() {
          return delFoodmenu(ids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 推荐食物详情序号 */
      rowBhMenuInfoIndex({
        row,
        rowIndex
      }) {
        row.index = rowIndex + 1;
      },
      /** 推荐食物详情添加按钮操作 */
      handleAddBhMenuInfo() {
        let obj = {};
        obj.foodId = "";
        obj.type = "";
        this.bhMenuInfoList.push(obj);
      },
      /** 推荐食物详情删除按钮操作 */
      handleDeleteBhMenuInfo() {
        if (this.checkedBhMenuInfo.length == 0) {
          this.$modal.msgError("请先选择要删除的推荐食物详情数据");
        } else {
          const bhMenuInfoList = this.bhMenuInfoList;
          const checkedBhMenuInfo = this.checkedBhMenuInfo;
          this.bhMenuInfoList = bhMenuInfoList.filter(function(item) {
            return checkedBhMenuInfo.indexOf(item.index) == -1
          });
        }
      },
      /** 复选框选中数据 */
      handleBhMenuInfoSelectionChange(selection) {
        this.checkedBhMenuInfo = selection.map(item => item.index)
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/foodmenu/export', {
          ...this.queryParams
        }, `foodmenu_${new Date().getTime()}.xlsx`)
      }
    }
  };
</script>
