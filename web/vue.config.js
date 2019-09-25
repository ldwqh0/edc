const webpack = require('webpack')

module.exports = {
  publicPath: process.env.CONTEXT_PATH,
  assetsDir: 'static',
  devServer: {
    proxy: {
      // 如果使用本地后台服务，请打开下列配置
      // 数据中心
      // '/dc/d/data/': {
      //   changeOrigin: true,
      //   target: 'http://127.0.0.1:8081/',
      //   pathRewrite: { '^/dc/d/data/': '' }
      // },
      // 可视化配置
      // '/dc/d/dvm/': {
      //   changeOrigin: true,
      //   target: 'http://127.0.0.1:8082/',
      //   pathRewrite: { '^/dc/d/dvm/': '' }
      // },
      // 应急指挥
      '/dc/d/ioc/': {
        changeOrigin: true,
        target: 'http://127.0.0.1:8083/',
        pathRewrite: { '^/dc/d/ioc/': '' }
      },
      '/dc/d/gather/': {
        changeOrigin: true,
        target: 'http://127.0.0.1:8084/',
        pathRewrite: { '^/dc/d/gather/': '' }
      },
      // 数据相关地址
      '/dc/d/': {
        changeOrigin: true,
        target: 'http://ldwqh0.kmdns.net:280',
      },
      // 用户管理相关地址
      '/dc/u/': {
        changeOrigin: true,
        target: 'http://ldwqh0.kmdns.net:280',
      },
      // 权限管理相关地址
      '/dc/p/': {
        changeOrigin: true,
        target: 'http://ldwqh0.kmdns.net:280',
      },
      // 认证的地址，单独填写
      '/oauth/': {
        changeOrigin: true,
        target: 'http://ldwqh0.kmdns.net:280',
      }
    },
  },
  configureWebpack: {
    entry: ['core-js/stable', 'regenerator-runtime/runtime', './src'],
    plugins: [
      new webpack.DefinePlugin({
        // 定义一个全局的上下文变量，目的是为了发布时能够通过目录区分不同的项目，而不用单独占用一个端口
        'CONTEXT_PATH': JSON.stringify(process.env.CONTEXT_PATH)
      })
    ]
  }
}
