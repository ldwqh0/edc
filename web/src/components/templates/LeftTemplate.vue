<template>
  <el-container style="height: 100%" class="app left-template">
    <el-header class="header">Header</el-header>
    <el-container style="overflow-y: auto">
      <el-aside class="aside"
                :width="collapse?'65px':'200px'">
        <a class="btn-collapse" @click="collapse=!collapse">
          <i class="el-icon-s-fold" v-if="!collapse" />
          <i class="el-icon-s-unfold" v-if="collapse" />
        </a>
        <el-menu background-color="#304156"
                 text-color="#FFFFFF"
                 :collapse="collapse"
                 style="border-right: none">
          <el-submenu index="1">
            <template v-slot:title>
              <i class="el-icon-location" />
              <span slot="title"> 子菜单1</span>
            </template>
            <el-menu-item index="2">菜单已</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
  import Vue from 'vue'
  import {Component} from 'vue-property-decorator'

  @Component
  export default class LeftTemplate extends Vue {
    collapse = false
  }
</script>

<style lang="less">
  html, body {
    height: 100%;
  }
</style>
<style lang="less" scoped>
  .app.left-template {
    .btn-collapse {
      display: block;
      text-align: center;
      font-size: 24px;
    }

    .header, aside {
      background: #304156;
      color: #d9d9d9;
    }

    .header {
      border-bottom: solid 1px #9c9c9c;
    }
  }
</style>
