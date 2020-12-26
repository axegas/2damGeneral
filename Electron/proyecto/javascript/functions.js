const BrowserWindow = require('electron').remote.BrowserWindow;

function newWin(a){
    const winNew = new BrowserWindow({
        webPreferences: {
            nodeIntegration: true,
            nativeWindowOpen: true
        }    
    });
    
    winNew.loadFile(a)
}


module.exports = {
    "newWin": newWin
}