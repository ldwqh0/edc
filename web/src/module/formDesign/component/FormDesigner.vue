<template>
  <el-container>
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
            <i class="icon iconfont" :class="item.icon" />
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
            <i class="icon iconfont" :class="item.icon" />
            <span>{{ item.name }}</span>
          </a>
        </li>
      </draggable>
    </el-aside>
    <widget-form :data="data" />
  </el-container>
</template>

<script>
  import Vue from 'vue'
  import Draggable from 'vuedraggable'
  import { Component } from 'vue-property-decorator'
  import { basicComponents, layoutComponents } from './componentsConfig'
  import WidgetForm from './WidgetForm'

  @Component({
    components: {
      Draggable,
      WidgetForm
    }
  })
  export default class FormDesigner extends Vue {
    basicComponents = basicComponents

    layoutComponents = layoutComponents

    data = { list: [] }
  }
</script>

<style scoped>
  .form-edit-widget-label {
    list-style-type: none;
  }
</style>
