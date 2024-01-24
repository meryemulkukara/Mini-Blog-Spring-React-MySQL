import React,{useState} from "react";
//import { ReactDOM } from "react";
//import ReactDOM from "react-dom";

import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import { purple } from '@mui/material/colors';
import { Link } from "react-router-dom";
import { Button, InputAdornment} from "@mui/material";
import OutlinedInput from '@mui/material/OutlinedInput';
import Snackbar from '@mui/material/Snackbar';
import Alert from '@mui/material/Alert';



function Comment({userid, username, text}){

    return(
        <Card>
            
            <OutlinedInput
                disabled
                id='outlined-adormen-amount'
                multiline
                inputProps={{maxLength:25}}
                fullWidth
                value={text}
                startAdornment= { <InputAdornment position="start">
                                   {
                                        <Link style={{ textDecoration: 'none' }} to={{pathname: '/users/' + userid}} >           
                                            <Avatar  sx={{ bgcolor: "#e1bee7" }} aria-label="recipe">
                                                {username.charAt(0).toUpperCase()}   
                                            </Avatar>
                                        </Link>}
                                    </InputAdornment>}
            ></OutlinedInput>
        </Card>
    )


}


export default Comment;