import React from 'react';
import { useRoute, useRouteNode } from 'react-router5'
import { makeStyles } from '@material-ui/core/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Menu from "../Components/Menu/Menu";
import PeopleIcon from "@material-ui/icons/People";
import AccountBalanceIcon from "@material-ui/icons/AccountBalance";
import HomeIcon from "@material-ui/icons/Home";
import Users from "./Users/Users";
import Accounts from "./Accounts/Accounts";
import Home from "./Home/Home";

const drawerWidth = 240;
const useStyles = makeStyles((theme) => ({
    root: {
        display: 'flex',
    },
    appBar: {
        width: `calc(100% - ${drawerWidth}px)`,
        marginLeft: drawerWidth,
    },
    toolbar: theme.mixins.toolbar,
    content: {
        flexGrow: 1,
        backgroundColor: theme.palette.background.default,
        padding: theme.spacing(3),
    },
}));

function App() {
    const {router} = useRoute();
    const { route } = useRouteNode('')
    const classes = useStyles();

    return (
        <div className={classes.root}>
            <CssBaseline/>
            <AppBar position="fixed" className={classes.appBar}>
                <Toolbar>
                    <Typography variant="h6" noWrap>
                        Interview Template Client
                    </Typography>
                </Toolbar>
            </AppBar>
            <Menu variant="permanent"
                  menuItems={[
                      {name: 'Home', routeName: 'home', icon: <HomeIcon/>},
                      {name: 'Users', routeName: 'users', icon: <PeopleIcon/>},
                      {name: 'Accounts', routeName: 'accounts', icon: <AccountBalanceIcon/>}]}
                  router={router}
            />
            <main className={classes.content}>
                <div className={classes.toolbar}/>
                {route.name === 'home' && <Home />}
                {route.name === 'users' && <Users />}
                {route.name === 'accounts' && <Accounts />}
            </main>
        </div>
    );
}

export default App;
