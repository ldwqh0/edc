<!--表单设计器-->
<template>
  <el-container>
    <!--左侧为控件列表-->
    <el-aside width="250px">
      <draggable tag="ul"
                 :list="basicComponents"
                 :group="{ name:'people', pull:'clone',put:false}"
                 :sort="false"
                 ghost-class="ghost">
        <li class="form-edit-widget-label"
            :class="{'no-put': item.type == 'divider'}"
            v-for="(item, index) in basicComponents"
            :key="index">
          <a>
            <i class="icon iconfont" :class="item.icon"/>
            <span>{{ item.name }}</span>
          </a>
        </li>
      </draggable>
      <div class="widget-cate">布局字段</div>
      <draggable tag="ul"
                 :list="layoutComponents"
                 v-bind="{group:{ name:'people', pull:'clone',put:false},sort:false, ghostClass: 'ghost'}">
        <li class="form-edit-widget-label no-put"
            v-for="(item, index) in layoutComponents"
            :key="index">
          <a>
            <i class="icon iconfont" :class="item.icon"/>
            <span>{{ item.name }}</span>
          </a>
        </li>
      </draggable>
    </el-aside>
    <!--中间为表单设计页面-->
    <el-main>
      <el-form>
        <el-row>
          <el-col>
            <el-button size="mini" type="primary" @click="vis=true">预览</el-button>
            <el-button size="mini" type="primary" @click="submit">保存</el-button>
          </el-col>
        </el-row>
      </el-form>
      <!--      <el-button @click="vis=true">预览</el-button>-->
      <widget-form :data="data" @active-change="activeItem=$event"/>
    </el-main>
    <el-aside width="250px">
      <widget-properties :widget="activeItem"/>
    </el-aside>
    <el-dialog :visible.sync="vis" v-if="vis" :close-on-click-modal="false">
      <form-view :form="data"/>
    </el-dialog>
  </el-container>
</template>

<script>
  import Vue from 'vue'
  import Draggable from 'vuedraggable'
  import { Component, Prop } from 'vue-property-decorator'
  import { basicComponents, layoutComponents } from './componentsConfig'
  import WidgetForm from './WidgetForm'
  import WidgetProperties from './widgetProperties'
  import FormView from '../../form-viewer/FormViewer'
  import { namespace } from 'vuex-class'

  const formModule = namespace('formDesign')
  @Component({
    components: {
      Draggable,
      WidgetForm,
      WidgetProperties,
      FormView
    }
  })
  export default class FormDesigner extends Vue {
    @Prop({ default: () => 'new' })
    id
    basicComponents = basicComponents

    layoutComponents = layoutComponents

    activeItem = {}

    /**
     * 表单预览是否可见
     * @type {boolean}
     */
    vis = false

    @formModule.Action('save')
    save

    @formModule.Action('update')
    update

    @formModule.Action('load')
    load

    /**
     * 设计好的表单数据
     * @type {{list: []}}
     */
    data = {
      name: '',
      memo: '',
      action: '',
      options: {},
      widgets: []
    }

    created () {
      if (this.id !== 'new') {
        this.load(this.id).then(({ data }) => (this.data = data))
      }
    }

    submit () {
      if (this.id === 'new') {
        this.save(this.data)
      } else {
        this.update(this.data)
      }
    }
  }
</script>

<style scoped>
  .form-edit-widget-label {
    list-style-type: none;
  }
</style>
