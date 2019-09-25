<template xmlns:el-col="http://www.w3.org/1999/html">
  <el-container id="app" class="app" v-loading="loading">
    <el-header class="app-header">
      <el-row>
        <el-col :span="24" class="title-context">
          <el-menu mode="horizontal"
                   background-color="#1d2023"
                   active-text-color="#fff">
            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-s-unfold" style="font-size: 30px;"/>
              </template>
              <menu-item v-for="menu in systemMenus"
                         :key="menu.id"
                         :item="menu"/>
            </el-submenu>
          </el-menu>
          <router-link class="title" to="/">EDC</router-link>
          <el-menu class="pull-right"
                   mode="horizontal"
                   background-color="#1d2023">
            <el-submenu index="1">
              <template slot="title">
                <img class="avatar" src="@/assets/timg.jpg" alt="">
                <span>{{ displayUsername }}</span>
              </template>
              <el-menu-item @click="doLogout" index="2-1" style="color: white">退出</el-menu-item>
            </el-submenu>
          </el-menu>
        </el-col>
      </el-row>
    </el-header>
    <el-main class="app-main">
      <el-breadcrumb separator="/"
                     v-if="crumbs && crumbs.length>0">
        <el-breadcrumb-item v-for="crumb in crumbs"
                            :key="crumb.id">
          {{ crumb.title }}
        </el-breadcrumb-item>
      </el-breadcrumb>
      <router-view/>
    </el-main>
  </el-container>
</template>

<script>
  import Vue from 'vue'
  import {Component} from 'vue-property-decorator'
  import {State, Getter, namespace} from 'vuex-class'
  import MenuItem from '@/components/theme/MenuItem'

  const securityModule = namespace('security')
  @Component({
    components: {
      MenuItem
    }
  })
  export default class HeaderTemplate extends Vue {
    @securityModule.Getter('menuTree')
    menus

    @Getter('loading')
    loading

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

    @securityModule.State('user')
    currentUser

    get systemMenus () {
      const menus = this.menus.filter(menu => menu.name === '数据中心')
      if (menus !== null && menus !== undefined && menus.length > 0) {
        return menus[0].submenus
      } else {
        return []
      }
    }

    get displayUsername () {
      const {fullname, username} = this.currentUser
      return fullname === undefined || fullname === null ? username : fullname
    }

    doLogout () {
      this.logout().then(() => {
        this.$router.push({name: 'login'})
      })
    }

    created () {
      // this.loadUserInfo()
      // this.loadMenu()
    }
  }
</script>

<style lang="less">

  .app {
    .app-header {
      background: #1D2023;
      overflow: hidden;
      padding: 0 10px;

      .title-context > * {
        vertical-align: middle;
        border-bottom-width: 0px;
      }

      .el-menu {
        display: inline-block;
      }

      .el-submenu__title {
        border: none;
        padding: 0;
      }

      a.router-link-active {
        color: white;
        text-decoration: none;
      }

      .title {
        font-size: 26px;
        margin-left: 10px;
        color: #fff;
      }

      img.avatar {
        display: inline-block;
        width: 40px;
        height: 40px;
        border-radius: 100%;
      }

    }

    .app-main {
      .el-breadcrumb { /*重新定义导航面包屑的样式*/
        margin: -20px -20px 10px -20px;
        padding: 10px;
        background: #D9DBDB;
      }
    }
  }

</style>
