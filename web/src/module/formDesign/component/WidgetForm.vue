<!--设计表单-->
<template>
  <el-form label-width="100px"
           :model="formData"
           class="design-form"
           :class="{active: selectWidget.type == 'form'}"
           @click.native.self="select({type:'form',form:data})">
    <draggable class=""
               v-model="data.widgets"
               group="people"
               ghost-class="ghost"
               :animation="200"
               handle=".drag-widget"
               @add="handleWidgetAdd">
      <transition-group name="fade" tag="div" class="widget-form-list">
        <template v-for="(element, index) in data.widgets">
          <template v-if="element.type === 'grid'">
            <el-row class="widget-row widget-view"
                    v-if="element.key"
                    :key="element.key"
                    type="flex"
                    @click.native.stop="select(element)"
                    :class="{active: selectWidget.key === element.key}"
                    :gutter="element.options.gutter ? element.options.gutter : 0"
                    :justify="element.options.justify"
                    :align="element.options.align">
              <el-col v-for="(col, colIndex) in element.columns" :key="colIndex" :span="col.span ? col.span : 0">
                <draggable v-model="col.list"
                           :no-transition-on-drag="true"
                           group="people"
                           ghost-class="ghost"
                           :animation="200"
                           handle=".drag-widget"
                           @add="handleWidgetColAdd($event, element, colIndex)">
                  <transition-group name="fade" tag="div" class="widget-col-list">
                    <template v-for="(el) in col.list">
                      <div class="widget-view item-view"
                           v-if="el.key"
                           :key="el.key"
                           @click.stop="select(el)"
                           :class="{active: selectWidget.key == el.key}">
                        <!--                        <widget-view/>-->
                        <form-item :widget="{}"/>
                        <i class="iconfont icon-drag drag-widget"/>
                        <div class="widget-view-action">
                          <!--复制作为保留功能-->
                          <!--<i class="iconfont icon-icon_clone" @click.stop="handleWidgetClone(index)"></i>-->
                          <i class="iconfont icon-trash"/>
                        </div>
                      </div>
                    </template>
                  </transition-group>
                </draggable>
              </el-col>
              <i class="iconfont icon-drag drag-widget"/>
              <div class="widget-view-action">
                <i class="iconfont icon-trash" @click.stop="handleWidgetDelete(index)"/>
              </div>
            </el-row>
          </template>
          <template v-else>
            <!--只有在元素被添加，并且生成唯一key之后，才进行渲染-->
            <div class="widget-view item-view"
                 v-if="element.key"
                 :key="element.key"
                 @click="select(element)"
                 :class="{active: selectWidget.key == element.key}">
              <!--              <widget-view :value="element"/>-->
              <form-item :widget="element" :data="formData"/>
              <i class="iconfont icon-drag drag-widget"/>
              <div class="widget-view-action" v-if="selectWidget.key == element.key">
                <!--复制作为保留功能-->
                <!--<i class="iconfont icon-icon_clone" @click.stop="handleWidgetClone(index)"></i>-->
                <i class="iconfont icon-trash"/>
              </div>
            </div>
          </template>
        </template>
      </transition-group>
    </draggable>
  </el-form>
</template>

<script>
  import Draggable from 'vuedraggable'
  import Vue from 'vue'
  import { Component, Prop } from 'vue-property-decorator'
  import { randomKey } from './utils'
  import { FormItem } from '../../form-viewer'

  @Component({
    components: {
      FormItem,
      Draggable
    }
  })
  export default class WidgetForm extends Vue {
    // 定义的表单数据
    @Prop({})
    data

    formData = {}

    selectWidget = {}

    select (v) {
      this.selectWidget = v
      this.$emit('active-change', v)
    }

    created () {
      console.log(this.data)
    }

    /**
     * 将对象添加到指定位置之后，数字中存储的是之前的对象引用，这里生成一个新的对象拷贝，并生成一个唯一KEY
     */
    addWidgetToList (index, list) {
      const item = JSON.parse(JSON.stringify(list[index]))
      Object.assign(item, { key: randomKey() })
      this.$set(list, index, item)
      this.select(item)
      return item
    }

    handleWidgetAdd ({ newIndex }) {
      // 为拖拽到容器的元素添加唯一 key
      // 为新增的项目设置key
      this.addWidgetToList(newIndex, this.data.widgets)
      // if (this.data.list[newIndex].type === 'radio' || this.data.list[newIndex].type === 'checkbox' || this.data.list[newIndex].type === 'select') {
      //   this.$set(this.data.list, newIndex, {
      //     ...this.data.list[newIndex],
      //     options: {
      //       ...this.data.list[newIndex].options,
      //       options: this.data.list[newIndex].options.options.map(item => ({
      //         ...item
      //       }))
      //     }
      //   })
      // }
      //
      // if (this.data.list[newIndex].type === 'grid') {
      //   this.$set(this.data.list, newIndex, {
      //     ...this.data.list[newIndex],
      //     columns: this.data.list[newIndex].columns.map(item => ({ ...item }))
      //   })
      // }
    }

    /**
     *
     * @param newIndex
     * 拖拽的index
     * @param oldIndex
     * @param item
     * @param row
     * 操作的行
     * @param colIndex
     * 操作的列
     * @returns {boolean}
     */
    handleWidgetColAdd ({ newIndex, oldIndex, item }, { columns }, colIndex) {
      // 获取对象
      this.addWidgetToList(newIndex, columns[colIndex].list)
      // 防止布局元素的嵌套拖拽
      // if (item.className.indexOf('data-grid') >= 0) {
      //   // 如果是列表中拖拽的元素需要还原到原来位置
      //   item.tagName === 'DIV' && this.data.list.splice(oldIndex, 0, row.columns[colIndex].list[newIndex])
      //   row.columns[colIndex].list.splice(newIndex, 1)
      //   return false
      // }

      // if (row.columns[colIndex].list[newIndex].type === 'radio' || row.columns[colIndex].list[newIndex].type === 'checkbox' || row.columns[colIndex].list[newIndex].type === 'select') {
      //   this.$set(row.columns[colIndex].list, newIndex, {
      //     ...row.columns[colIndex].list[newIndex],
      //     options: {
      //       ...row.columns[colIndex].list[newIndex].options,
      //       options: row.columns[colIndex].list[newIndex].options.options.map(item => ({
      //         ...item
      //       }))
      //     }
      //   })
      // }
    }
  }
</script>
<style lang="less">
  .design-form {
    padding: 5px;
    border: dashed 1px #999999;

    &.active {
      border: solid 3px #409EFF;
    }
  }

  .widget-form-list {
    min-height: 600px;
    border: dashed 1px #999999;

    .ghost {
      border: dashed 1px red;
    }

    // 组件容器，包括row的容器，控件的容器
    .widget-view {
      position: relative;

      // 拖动柄和操作柄在默认情况下不可见
      .drag-widget, .widget-view-action {
        display: none;
        position: absolute;
        background-color: #409EFF;
        padding: 3px;
        color: white;
      }

      .drag-widget {
        left: 0;
        top: 0;
        line-height: 20px;
        margin: -3px 0px 0px -3px;
      }

      .widget-view-action {
        right: 0;
        bottom: 0;
        margin: 0px -3px -3px 0px;
      }

      &.active {
        > .drag-widget, > .widget-view-action {
          display: block;
        }

        .drag-widget {
          cursor: move;
        }

        border: solid 3px #409EFF;
      }
    }

    /*控件显示组件的样式*/

    .item-view {

    }

    .widget-row {
      border: dashed 1px #ff7172;
      padding: 1px;
    }

    .widget-col-list {
      min-height: 20px;
      border: dashed 1px #999999;
      padding: 2px;
      margin: 2px;
    }
  }

</style>

<style scoped>

</style>
