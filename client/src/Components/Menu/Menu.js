import React from "react";
import Drawer from "@material-ui/core/Drawer";
import Divider from "@material-ui/core/Divider";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemIcon from "@material-ui/core/ListItemIcon";
import { BaseLink } from "react-router5";
import * as PropTypes from "prop-types";
import { makeStyles } from "@material-ui/core/styles";

const drawerWidth = 240;

const useStyles = makeStyles((theme) => ({
    drawer: {
        width: drawerWidth,
        flexShrink: 0,
    },
    drawerPaper: {
        width: drawerWidth,
    },
    toolbar: theme.mixins.toolbar
}));

function Menu(props) {
    const classes = useStyles();
    return (
        <Drawer
            className={classes.drawer}
            variant={props.variant}
            classes={{
                paper: classes.drawerPaper,
            }}
            anchor="left"
        >
            <div className={classes.toolbar}/>
            <Divider/>
            <List>
                {props.menuItems.map((item, key) => (
                        <ListItem button key={key}>
                            <ListItemIcon>
                                {item.icon}
                            </ListItemIcon>
                            <BaseLink
                                router={props.router}
                                routeName={item.routeName}
                            >
                                {item.name}
                            </BaseLink>
                        </ListItem>
                    )
                )}
            </List>
        </Drawer>
    );
}

Menu.propTypes = {
    variant: PropTypes.string,
    menuItems: PropTypes.array,
    router: PropTypes.any
};

export default Menu;
