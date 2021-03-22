import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import Button from "@material-ui/core/Button";
import ButtonGroup from "@material-ui/core/ButtonGroup";
import { blue } from "@material-ui/core/colors";

const useStyles = makeStyles((theme) => ({
  appBar: {
    backgroundColor: "#fff",
  },
  title: {
    color: "#0000FF",
  },

  work: {
    marginLeft: "1000px",
    display: "flex",
    flexDirection: "row",
    justifyContent: "right",
    color: "#5944DE",
    fontWeight: 800,
    fontSize: "16px",
    background: "rgba(196, 196, 196, 0.22)",
    borderRadius: "50%",
    paddingLeft: "27px",
    paddingRight: "27px",
  },
  hire: {
    marginLeft: "90px",
    display: "flex",
    flexDirection: "row",
    justifyContent: "right",
    fontSize: "16px",
    fontWeight: 800,
    color: "#F11818",
  },
}));

function Nav() {
  const classes = useStyles();
  return (
    <div className={classes.root}>
      <AppBar className={classes.appBar} position="relative">
        <Toolbar>
          <Typography
            variant="h6"
            color="textSecondary"
            className={classes.title}
          >
            Soft Work
          </Typography>

          <Button
            variant="contained"
            href="#contained-buttons"
            className={classes.work}
          >
            Login
          </Button>
          <Button
            // variant="contained"
            href="#contained-buttons"
            className={classes.hire}
          >
            Sign Up
          </Button>
        </Toolbar>
      </AppBar>
    </div>
  );
}

export default Nav;
