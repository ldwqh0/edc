<template>
  <div class="login">
    <div v-if="useSso">登录中...</div>
    <el-form class="content" v-if="!useSso">
      <div class="title">枣庄智慧旅游云大数据平台</div>
      <el-form-item label="用户名：" label-width="80px">
        <el-input v-model="data.username"/>
      </el-form-item>
      <el-form-item label="密码：" label-width="80px">
        <el-input v-model="data.password"/>
      </el-form-item>
      <el-button @click="login" type="primary">登录</el-button>
    </el-form>
  </div>
</template>

<script>
  import Vue from 'vue'
  import { Component, Prop } from 'vue-property-decorator'
  import { namespace } from 'vuex-class'
  import qs from 'qs'
  import translateUrl from './translateUrl'

  const oauthModule = namespace('security')

  function getCurrentPath () {
    let {
      protocol,
      host,
      pathname
    } = window.location
    return `${protocol}//${host}${pathname}`
  }

  @Component({
    components: {}
  })
  export default class Login extends Vue {
    @Prop({})
    redirect

    @Prop({ default: () => '' })
    code

    @Prop({ default: () => '' })
    state

    // 是否启用单点登录，这是一个全局变量
    useSso = USE_SSO

    data = {
      'grant_type': 'password',
      'client_id': 'ownerapp',
      'client_secret': '123456',
      'username': 'admin',
      'password': '123456'
    }

    @oauthModule.Action('token')
    getToken

    @oauthModule.Action('login')
    doLogin

    created () {
      if (this.useSso) {
        if (this.code !== '' && this.state !== '') {
          // 自动登录
          this.doLogin({ code: this.code, state: this.state, current_uri: getCurrentPath() }).then(this.goHome)
        } else {
          // 如果没有检测到授权码，发起一次登录请求，获取跳转链接
          this.doLogin().then(() => {})
            .catch(({ response: { data: { redirect_url: redirectUrl } } }) => {
              // 保存当前会话地址
              if (this.redirect) {
                sessionStorage.setItem('savedRedirect', this.redirect)
              }

              let [server, paramStr] = redirectUrl.split('?')
              let { protocol, host } = window.location
              let { pathname } = translateUrl(server)
              let parameters = qs.parse(paramStr)
              server = `${protocol}//${host}/${pathname}`
              parameters.redirect_uri = getCurrentPath()
              window.location.href = `${server}?${qs.stringify(parameters)}`
            })
        }
      }
    }

    goHome () {
      // 如果保存的有历史，要返回历史
      let savedUrl = sessionStorage.getItem('savedRedirect')
      if (
        savedUrl !== null &&
        savedUrl !== undefined &&
        savedUrl !== ''
      ) {
        let route = JSON.parse(savedUrl)
        this.$router.replace(route)
      } else {
        this.$router.push({
          path: '/'
        })
      }
      sessionStorage.removeItem('savedRedirect')
    }

    // 手动登录按钮
    login () {
      // this.doLogin({ code: this.code, state: this.state, current_uri: getCurrentPath() })
      this.getToken(this.data).then(this.goHome)
    }
  }
</script>

<style lang="less">
  * {
    margin: 0;
    padding: 0;
  }

  html, body {
    width: 100%;
    height: 100%;
  }

  li {
    list-style: none;
  }

  .login {
    width: 100%;
    height: 100%;
    background: #2d3a4b;
    display: flex;
    justify-content: center;
    align-items: center;

    .content {
      width: 450px;
      height: 300px;

      .title {
        width: 100%;
        height: 80px;
        font-size: 25px;
        line-height: 80px;
        text-align: center;
        color: white;
      }

      .el-form-item__label {
        font-size: 16px;
        color: white;
      }

      .el-button {
        float: right;
        margin: 25px 0 0 0;
      }
    }
  }
</style>
