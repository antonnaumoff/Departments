const webpack = require("webpack");
const path = require('path');

const DEV = process.env.NODE_ENV !== 'production';

module.exports = {
    entry: {
        app: ["./src/main.js"]
    },
    resolve: {
        modulesDirectories: [
            "node_modules"
        ],
        extensions: ['', '.json', '.js']
    },
    resolveLoader: {
        modulesDirectories: ['node_modules']
    },
    plugins: [
      new webpack.EnvironmentPlugin('NODE_ENV')
    ],
    node: {
        fs: "empty"
    },
    module: {
      loaders: [{
          test: /\.js$/,
          loader: "babel"
      }]
    },
    output: {
        path: path.resolve(__dirname, "build"),
        filename: "bundle.js"
    },
    devServer: {
        contentBase: './build'
    },
    devtool: "source-map"
}