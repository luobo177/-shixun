const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 8080,
    proxy: {
      '/api': {
        target: 'http://localhost:8081', // 修改为后端服务器地址
        changeOrigin: true, // 允许跨域
        // pathRewrite: {  // 不需要重写路径，因为后端也使用 /api 前缀
        //   '^/api': ''
        // }
      }
    }
  }
})

// const { defineConfig } = require('@vue/cli-service');

// module.exports = defineConfig({
//   transpileDependencies: true,
//   devServer: {
//     proxy: {
//       '/api': {
//         target: 'http://localhost:8112', // 将代理目标指向后端服务器地址
//         changeOrigin: true, // 允许跨域
//         pathRewrite: {
//           '^/api': '' // 去掉请求中的 /api 前缀
//         }
//       }
//     }
//   }
// });

