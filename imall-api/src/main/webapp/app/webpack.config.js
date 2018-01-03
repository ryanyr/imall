var webpack = require('webpack');
var path = require('path');
var webpackDevServer=require("webpack-dev-server")
var OpenBrowserPlugin = require('open-browser-webpack-plugin');

module.exports = {
    entry : [
    	'webpack-dev-server/client?http://localhost:8000',
        // i've also tried webpack/hot/dev-server here
        'webpack/hot/only-dev-server',
    	__dirname + "/src/js/main.js"
    ],
    output : {
        path : __dirname +"/dist/",
        filename : "app.js"
    },
    devtool : "source-map",
    devServer : {
        contentBase :  __dirname + "/dist/",
        historyApiFallback: true,
        hot: true,
        inline: true,
        progress: true,
        port: 8000,
        proxy: {
         '\*': {
           target: 'http://localhost:8080/app/dist/',
           secure: false
         }
        } 
    },
    module : {
        loaders : [
            {test : /\.js$/,exclude : /node_modules/,loader:"babel-loader?presets[]=es2015&presets[]=react"},
            {test : /\.css$/,loader:"style-loader!css-loader"},
            {test : /\.less$/,loader:"style-loader!css-loader!less-loader"},
        ]
    },
    plugins : [
        new webpack.HotModuleReplacementPlugin(),
        new webpack.ProvidePlugin({
            React : "react",
            $:"jquery",
        }),
        new OpenBrowserPlugin({
            url: 'http://localhost:8000'
        })        
    ],  
}