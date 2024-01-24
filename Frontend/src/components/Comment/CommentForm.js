import React,{useState} from "react";
//import { ReactDOM } from "react";
//import ReactDOM from "react-dom";

import Card from '@mui/material/Card';
import Avatar from '@mui/material/Avatar';
import { purple } from '@mui/material/colors';
import { Link } from "react-router-dom";
import { Button, InputAdornment} from "@mui/material";
import OutlinedInput from '@mui/material/OutlinedInput';
import Snackbar from '@mui/material/Snackbar';
import Alert from '@mui/material/Alert';




const pushButton={
    backgroundColor:purple[100],
    color:"white"
}

function CommentForm({userid,username, postid, refreshComments}){

    const[ text, setText] = useState('');
    const[ isSent, setIsSent]= useState(false);

    const handleText= (value) => {
        setText(value);
        //setIsSent(false);
    }


    const savePost=() =>{
        fetch("/comments",
        {
            method:"POST",
            headers:{ 'Accept': 'application/json',
            'Content-Type': 'application/json'},
            body:JSON.stringify({
                text:text,
                userid:userid,
                postid:postid
            }),
        }).then((res) => res.json()).catch((err)=>console.log('error')) 
        //.then((res) => res.json()).then(data => console.log(data)).catch((err)=>console.log('error')) 
        
    }

    const handleSubmit= () => {
        //console.log(title, text);
        savePost();
        
        setIsSent(true);
        setText("");
        refreshComments();
      }
  

    return(
        <Card>
            
            <OutlinedInput
                id='outlined-adormen-amount'
                multiline
                placeholder="Text"
                inputProps={{maxLength:250}}
                fullWidth
                value={text}
                onChange={i => handleText( i.target.value)}
                startAdornment= { <InputAdornment position="start">
                                   {
                                        <Link style={{ textDecoration: 'none' }} to={{pathname: '/users/' + userid}} >           
                                            <Avatar  sx={{ bgcolor: "#e1bee7" }} aria-label="recipe">
                                                {username.charAt(0).toUpperCase()}   
                                            </Avatar>
                                        </Link>}
                                    </InputAdornment>}
                endAdornment={
                    <InputAdornment position="end">
                    <Button variant="contained"  style={pushButton }
                        onClick={handleSubmit}>Post</Button>
                    </InputAdornment>}
            ></OutlinedInput>
        </Card>
    )


}


export default CommentForm;