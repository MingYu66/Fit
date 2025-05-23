<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="120px">
      <el-form-item label="食物名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入食物名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否是液体" prop="isLiquid">
        <el-input
          v-model="queryParams.isLiquid"
          placeholder="请输入是否是液体"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="推荐食用等级" prop="healthLight">
        <el-input
          v-model="queryParams.healthLight"
          placeholder="推荐食用等级"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:food:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:food:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:food:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:food:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="foodList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <!-- <el-table-column label="编码" align="center" prop="code" /> -->
      <el-table-column label="分类" align="center" prop="pid" >
      <template slot-scope="scope">
        {{ formatName(scope.row.pid) }}
      </template>
      </el-table-column>
      <el-table-column label="食物名称" align="center" prop="name" />

      <el-table-column label="缩略图" align="center" prop="thumbImageUrl" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.thumbImageUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column show-overflow-tooltip label="食材内容" align="center" prop="descInfo" />
      <!-- <el-table-column label="是否是液体" align="center" prop="isLiquid" /> -->
      <el-table-column label="健康度/推荐食用等级" align="center" prop="healthLight" />
      <el-table-column label="重量/每100g" align="center" prop="weight" />
      <el-table-column label="卡路里" align="center" prop="calory" />
      <el-table-column label="脂肪" align="center" prop="fat" />
      <el-table-column label="蛋白质" align="center" prop="protein" />
      <el-table-column label="膳食纤维" align="center" prop="fiberDietary" />
      <el-table-column label="碳水化合物" align="center" prop="carbohydrate" />
      <el-table-column label="维生素A" align="center" prop="vitaminA" />
      <el-table-column label="维生素B1" align="center" prop="thiamine" />
      <el-table-column label="维生素B2" align="center" prop="lactoflavin" />
      <el-table-column label="维生素C" align="center" prop="vitaminC" />
      <el-table-column label="维生素E" align="center" prop="vitaminE" />
      <el-table-column label="烟酸" align="center" prop="niacin" />
      <el-table-column label="钠" align="center" prop="natrium" />
      <el-table-column label="钙" align="center" prop="calcium" />
      <el-table-column label="铁" align="center" prop="iron" />
      <el-table-column label="钾" align="center" prop="kalium" />
      <el-table-column label="碘" align="center" prop="iodine" />
      <el-table-column label="锌" align="center" prop="zinc" />
      <el-table-column label="硒" align="center" prop="selenium" />
      <el-table-column label="镁
 " align="center" prop="magnesium" />
      <el-table-column label="铜" align="center" prop="copper" />
      <el-table-column label="锰" align="center" prop="manganese" />
      <el-table-column label="胆固醇" align="center" prop="cholesterol" />
      <el-table-column label="叶酸" align="center" prop="folacin" />
      <el-table-column label="饱和脂肪" align="center" prop="saturatedFat" />
      <el-table-column label="糖" align="center" prop="sugar" />
      <el-table-column label="维生素B6" align="center" prop="vitaminB6" />
      <el-table-column label="磷" align="center" prop="phosphor" />
      <el-table-column label="GI" align="center" prop="gi" />
      <el-table-column label="GL" align="center" prop="gl" />

      <el-table-column label="操作" align="center" fixed="right" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:food:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:food:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改食物管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <!-- <el-form-item label="编码" prop="code">
          <el-input v-model="form.code" placeholder="请输入编码" />
        </el-form-item> -->
        <el-form-item label="分类" prop="pid">
          <el-select v-model="form.pid" placeholder="请选择">
              <el-option
                v-for="item in categoriesList"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="食物名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入食物名称" />
        </el-form-item>
        <el-form-item label="缩略图" prop="thumbImageUrl">
          <image-upload v-model="form.thumbImageUrl"/>
        </el-form-item>
        <el-form-item label="食材内容" prop="descInfo">
          <el-input type="textarea" v-model="form.descInfo" placeholder="请输入食材内容" />
        </el-form-item>
        <!-- <el-form-item label="是否是液体" prop="isLiquid">
          <el-input v-model="form.isLiquid" placeholder="请输入是否是液体" />
        </el-form-item> -->
        <el-form-item label="健康度/推荐食用等级" prop="healthLight">
          <el-input type="number" min="1" max="4" v-model="form.healthLight" placeholder="请输入1-4" />
        </el-form-item>
        <el-form-item label="重量/每100g" prop="weight">
          <el-input v-model="form.weight" placeholder="请输入重量/每100g" />
        </el-form-item>
        <el-form-item label="卡路里" prop="calory">
          <el-input v-model="form.calory" placeholder="请输入卡路里" />
        </el-form-item>
        <el-form-item label="脂肪" prop="fat">
          <el-input v-model="form.fat" placeholder="请输入脂肪" />
        </el-form-item>
        <el-form-item label="蛋白质" prop="protein">
          <el-input v-model="form.protein" placeholder="请输入蛋白质" />
        </el-form-item>
        <el-form-item label="膳食纤维" prop="fiberDietary">
          <el-input v-model="form.fiberDietary" placeholder="请输入膳食纤维" />
        </el-form-item>
        <el-form-item label="碳水化合物" prop="carbohydrate">
          <el-input v-model="form.carbohydrate" placeholder="请输入碳水化合物" />
        </el-form-item>
        <el-form-item label="维生素A" prop="vitaminA">
          <el-input v-model="form.vitaminA" placeholder="请输入维生素A" />
        </el-form-item>
        <el-form-item label="维生素B1" prop="thiamine">
          <el-input v-model="form.thiamine" placeholder="请输入维生素B1" />
        </el-form-item>
        <el-form-item label="维生素B2" prop="lactoflavin">
          <el-input v-model="form.lactoflavin" placeholder="请输入维生素B2" />
        </el-form-item>
        <el-form-item label="维生素C" prop="vitaminC">
          <el-input v-model="form.vitaminC" placeholder="请输入维生素C" />
        </el-form-item>
        <el-form-item label="维生素E" prop="vitaminE">
          <el-input v-model="form.vitaminE" placeholder="请输入维生素E" />
        </el-form-item>
        <el-form-item label="烟酸" prop="niacin">
          <el-input v-model="form.niacin" placeholder="请输入烟酸" />
        </el-form-item>
        <el-form-item label="钠" prop="natrium">
          <el-input v-model="form.natrium" placeholder="请输入钠" />
        </el-form-item>
        <el-form-item label="钙" prop="calcium">
          <el-input v-model="form.calcium" placeholder="请输入钙" />
        </el-form-item>
        <el-form-item label="铁" prop="iron">
          <el-input v-model="form.iron" placeholder="请输入铁" />
        </el-form-item>
        <el-form-item label="钾" prop="kalium">
          <el-input v-model="form.kalium" placeholder="请输入钾" />
        </el-form-item>
        <el-form-item label="碘" prop="iodine">
          <el-input v-model="form.iodine" placeholder="请输入碘" />
        </el-form-item>
        <el-form-item label="锌" prop="zinc">
          <el-input v-model="form.zinc" placeholder="请输入锌" />
        </el-form-item>
        <el-form-item label="硒" prop="selenium">
          <el-input v-model="form.selenium" placeholder="请输入硒" />
        </el-form-item>
        <el-form-item label="镁
 " prop="magnesium">
          <el-input v-model="form.magnesium" placeholder="请输入镁
 " />
        </el-form-item>
        <el-form-item label="铜" prop="copper">
          <el-input v-model="form.copper" placeholder="请输入铜" />
        </el-form-item>
        <el-form-item label="锰" prop="manganese">
          <el-input v-model="form.manganese" placeholder="请输入锰" />
        </el-form-item>
        <el-form-item label="胆固醇" prop="cholesterol">
          <el-input v-model="form.cholesterol" placeholder="请输入胆固醇" />
        </el-form-item>
        <el-form-item label="叶酸" prop="folacin">
          <el-input v-model="form.folacin" placeholder="请输入叶酸" />
        </el-form-item>
        <el-form-item label="饱和脂肪" prop="saturatedFat">
          <el-input v-model="form.saturatedFat" placeholder="请输入饱和脂肪" />
        </el-form-item>
        <el-form-item label="糖" prop="sugar">
          <el-input v-model="form.sugar" placeholder="请输入糖" />
        </el-form-item>
        <el-form-item label="维生素B6" prop="vitaminB6">
          <el-input v-model="form.vitaminB6" placeholder="请输入维生素B6" />
        </el-form-item>
        <el-form-item label="磷" prop="phosphor">
          <el-input v-model="form.phosphor" placeholder="请输入磷" />
        </el-form-item>
        <el-form-item label="GI" prop="gi">
          <el-input v-model="form.gi" placeholder="请输入GI" />
        </el-form-item>
        <el-form-item label="GL" prop="gl">
          <el-input v-model="form.gl" placeholder="请输入GL" />
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
import { listFood, getFood, delFood, addFood, updateFood } from "@/api/system/food";
import { listCategories, getCategories, delCategories, addCategories, updateCategories } from "@/api/system/categories";
export default {
  name: "Food",
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
      // 食物管理表格数据
      foodList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        isLiquid: null,
        healthLight: null,
      },
      categoriesList:[],
      // 表单参数
      form: {},
      // 表单校验
       rules: {
              weight: [
                { required: true, message: "重量/每100g不能为空", trigger: "blur" }
              ],
              calory: [
                { required: true, message: "卡路里不能为空", trigger: "blur" }
              ],
              fat: [
                { required: true, message: "脂肪不能为空", trigger: "blur" }
              ],
              protein: [
                { required: true, message: "蛋白质不能为空", trigger: "blur" }
              ],
              carbohydrate: [
                { required: true, message: "碳水化合物不能为空", trigger: "blur" }
              ],
            }
    };
  },
  created() {

    listCategories({ pageNum: 1,
        pageSize: 100,
        name: null,}).then((response)=>{
        this.categoriesList = response.rows;
        this.getList();
    })
  },
  methods: {
    formatName(e){
      if(e != null){

        for(let i of this.categoriesList){
          if(i.id === e){
            return i.name
          }
        }
      }
       return '暂无';


    },
    /** 查询食物管理列表 */
    getList() {
      this.loading = true;
      listFood(this.queryParams).then(response => {
        this.foodList = response.rows;
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
        code: null,
        name: null,
        thumbImageUrl: null,
        isLiquid: null,
        healthLight: null,
        weight: 100,
        calory: null,
        fat: null,
        protein: null,
        fiberDietary: null,
        carbohydrate: null,
        vitaminA: null,
        thiamine: null,
        lactoflavin: null,
        vitaminC: null,
        vitaminE: null,
        niacin: null,
        natrium: null,
        calcium: null,
        iron: null,
        kalium: null,
        iodine: null,
        zinc: null,
        selenium: null,
        magnesium: null,
        copper: null,
        manganese: null,
        cholesterol: null,
        folacin: null,
        saturatedFat: null,
        sugar: null,
        vitaminB6: null,
        phosphor: null,
        gi: null,
        gl: null,
        pid: null
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
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加食物管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getFood(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改食物管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateFood(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFood(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除食物管理编号为"' + ids + '"的数据项？').then(function() {
        return delFood(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/food/export', {
        ...this.queryParams
      }, `food_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
