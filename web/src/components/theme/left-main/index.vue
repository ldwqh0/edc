<template>
  <el-container id="app" class="app">
    <el-header>
      <el-row class="title-wrapper">
        <el-col :span="12">
          <router-link to="/" class="title">枣庄指挥旅游大数据指挥平台</router-link>
        </el-col>
        <el-col :span="12">
          <el-menu mode="horizontal" background-color="#1D2023">
            <el-menu-item index="0"
                          style="padding: 0 15px;"
                          class="avatar">
              <img src="../../../assets/timg.jpg" alt="">
            </el-menu-item>
            <el-submenu index="2">
              <template slot="title">{{ displayUsername }}</template>
              <el-menu-item @click="doLogout" index="2-1" style="color: white">退出</el-menu-item>
            </el-submenu>
          </el-menu>
        </el-col>
      </el-row>
    </el-header>
    <el-container>
      <el-aside class="left-template">
        <el-menu :collapse="true"
                 style="min-height: 120px"
                 text-color="#FFF"
                 background-color="#1D2023">
          <menu-item v-for="(menu,index) in menus"
                     :key="index"
                     :item="menu"/>
        </el-menu>
      </el-aside>
      <el-main v-loading="routing">
        <el-row style="">
          <el-col :span="24">
            <el-breadcrumb separator="/"
                           v-if="crumbs && crumbs.length>0">
              <el-breadcrumb-item v-for="crumb in crumbs"
                                  :key="crumb.id">
                {{ crumb.title }}
              </el-breadcrumb-item>
            </el-breadcrumb>
          </el-col>
        </el-row>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
  import Vue from 'vue'
  import { Component } from 'vue-property-decorator'
  import { State, Getter, namespace } from 'vuex-class'
  import MenuItem from '../MenuItem'

  const securityModule = namespace('security')
  const regionModule = namespace('data/region')
  @Component({
    components: {
      MenuItem
    }
  })
  export default class Index extends Vue {
    @securityModule.Getter('menuTree')
    menus

    @securityModule.Action('logout')
    logout

    @State('routing')
    routing

    @Getter('crumbs')
    crumbs

    @securityModule.Action('loadMenu')
    loadMenu

    @securityModule.Action('loadUserInfo')
    loadUserInfo

    @regionModule.Action('loadRegions')
    loadRegion

    @securityModule.State('user')
    currentUser

    get displayUsername () {
      const { fullname, username } = this.currentUser
      return fullname === undefined || fullname === null ? username : fullname
    }

    doLogout () {
      this.logout().then(() => {
        this.$router.push({ name: 'login' })
      })
    }

    created () {
      this.loadUserInfo()
      this.loadMenu()
      this.loadRegion()
    }
  }
</script>

<style lang="less">

</style>
