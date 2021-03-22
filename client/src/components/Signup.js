import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Typography from "@material-ui/core/Typography";
import CardActionArea from "@material-ui/core/CardActionArea";
import CardMedia from "@material-ui/core/CardMedia";
import Button from "@material-ui/core/Button";
import Box from "@material-ui/core/Box";
import Container from "@material-ui/core/Container";
import ButtonGroup from "@material-ui/core/ButtonGroup";
import Nav from "./Nav";

const useStyles = makeStyles((theme) => ({
  App: {
    margin: 0,
    padding: 0,
    boxSizing: "border-box",
  },

  media: {
    position: "absolute",
    marginLeft: "450px",
    paddingLeft: "53.25%",
    paddingTop: "50.25%",
    marginTop: "68px",
    height: 10,
    margin: "5px",
  },
  cardActions: {
    dispaly: "flex",
    margin: "0, 10px",
    justifyContent: "space-between",
  },
  author: {
    display: "flex",
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
    fontSize: "25px",
    fontWeight: 400,
    color: "#1826F1",
  },
  root: {
    display: "flex",
    flexDirection: "row",
    marginLeft: 150,
    justifyContent: "flex-start",
    paddingTop: "47%",
    bottom: 0,
    "& > *": {
      margin: theme.spacing(2),
      paddingTop: "20px",
      paddingBottom: "20px",
      marginRight: "200px",
    },
  },

  hire: {
    paddingLeft: "40px",
    paddingRight: "40px",
    background: "#F11818",
  },
  work: {
    background: "none",
    paddingTop: "10px",
    paddingBottom: "10px",
    color: "#000",
    fontWeight: 800,
    fontSize: "20px",
  },
  blogsContainer: {
    paddingTop: theme.spacing(3),
    marginTop: 10,
    paddingBottom: 70,
    marginBottom: 100,
    width: "100%",
    boxShadow: "5px 10px 10px 5px #888888",
  },
  line: {
    border: 0,
    height: "2px",
    width: "70%",
    marginRight: "500px",
    background: "#333",
    marginTop: "3px",
    backgroundImage: "linear-gradient(to right, #ccc, #F11818, #ccc)",
  },
  text: {
    marginLeft: 180,
    fontSize: 25,
    paddingTop: 80,
  },
}));

function Signup() {
  const classes = useStyles();
  return (
    <div className={classes.App}>
      <Nav />
      <Container maxWidth="md" className={classes.blogsContainer}>
        <CardActionArea>
          <CardMedia
            className={classes.media}
            image="../../signup.svg"
            title="Contemplative Reptile"
          />
          <Box className={classes.textContent}>
            <Typography
              gutterBottom
              variant="h5"
              component="h2"
              className={classes.textTitle}
            >
              SignUp
            </Typography>
            <Typography
              variant="body2"
              color="textSecondary"
              component="p"
              className={classes.textBody}
            >
              SignUp in to continue to our application
            </Typography>
            <Typography
              variant="body2"
              color="textSecondary"
              component="p"
              className={classes.text}
            >
              with
            </Typography>
            <hr className={classes.line} />
          </Box>
        </CardActionArea>
        <ButtonGroup color="primary" className={classes.root}>
          <Button
            variant="contained"
            href="#contained-buttons"
            className={classes.work}
          >
            Github
          </Button>
        </ButtonGroup>
      </Container>
    </div>
  );
}

export default Signup;
