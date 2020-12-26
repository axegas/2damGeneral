const {app, BrowserWindow} = require('electron');

const path = require('path');
const url = require('url');
process.env['ELECTRON_DISABLE_SECURITY_WARNINGS'] = 'true';

function createWindow () {
    const mainWindow = new BrowserWindow({
        width: 800,
        height: 600,
        webPreferences: {
            nodeIntegration: true,
            enableRemoteModule: true
        }
    })
    // remove default chromium menu
    mainWindow.setMenu(null);
    
    mainWindow.loadFile('index.html')
    
    // Open the DevTools.
    //mainWindow.webContents.openDevTools ()
}

app.on('ready', createWindow);