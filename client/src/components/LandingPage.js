import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Typography from "@material-ui/core/Typography";
import CardActionArea from "@material-ui/core/CardActionArea";
import CardMedia from "@material-ui/core/CardMedia";
import Button from "@material-ui/core/Button";
import Box from "@material-ui/core/Box";
import ButtonGroup from "@material-ui/core/ButtonGroup";
import Nav from './Nav'

const useStyles = makeStyles((theme) => ({
  App: {
    margin: 0,
    padding: 0,
    boxSizing: "border-box",
  },

  media: {
    position: "absolute",
    marginLeft: "600px",
    paddingLeft: "56.25%",
    paddingTop: "50.25%",
    marginTop: "20px",
    width: "10px",
    borderRadius: "800%",
    margin: "18px",
  },
  cardActions: {
    dispaly: "flex",
    margin: "0, 10px",
    justifyContent: "space-between",
  },
  author: {
    display: "flex",
  },
  paginationContainer: {
    display: "flex",
    justifyContent: "center",
  },
  textContent: {
    position: "absolute",
    paddingTop: "12.25%",
    marginTop: "20px",
    width: "600px",
    margin: "18px",
    fontSize: "70px",
  },
  textTitle: {
    fontSize: "60px",
    fontWeight: 800,
  },
  textBody: {
    fontSize: "30px",
    fontWeight: 10,
    color: "#",
  },
  root: {
    display: "flex",
    flexDirection: "row",
    justifyContent: "flex-start",
    paddingTop: "42%",
    bottom: 0,
    "& > *": {
      margin: theme.spacing(2),
      paddingTop: "15px",
      paddingBottom: "15px",
      marginRight: "180px",
    },
  },

  hire: {
    paddingLeft: "40px",
    paddingRight: "40px",
    background: "#F11818",
  },
  work: {
    background: "#1826F1",
    paddingLeft: "30px",
    paddingRight: "30px",
  },
}));

function LandingPage() {
  const classes = useStyles();
  return (
    <div className={classes.App}>
      <Nav />
      <CardActionArea>
        <CardMedia
          className={classes.media}
          image="../../undraw_Co_workers_re_1i6i.svg"
          title="Contemplative Reptile"
        />
        <Box className={classes.textContent}>
          <Typography
            gutterBottom
            variant="h5"
            component="h2"
            className={classes.textTitle}
          >
            Work For Software Engineers
          </Typography>
          <Typography
            variant="body2"
            color="textSecondary"
            component="p"
            className={classes.textBody}
          >
            Hire professional software engineers using softwork.com
          </Typography>
        </Box>
      </CardActionArea>
      <ButtonGroup color="primary" className={classes.root}>
        <Button
          variant="contained"
          href="#contained-buttons"
          className={classes.work}
        >
          Start Work
        </Button>
        <Button
          variant="contained"
          href="#contained-buttons"
          className={classes.hire}
        >
          Hire Now
        </Button>
      </ButtonGroup>
    </div>
  );
}

export default LandingPage;
