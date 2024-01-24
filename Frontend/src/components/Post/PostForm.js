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



const pushButton={
    backgroundColor:purple[100],
    color:"white"
}

const linkStyle={ textDecoration: 'none'}

function PostForm({userid,username, refreshPost}){ //<Post title={post.title} text={post.text} />  Home  belirttiğmiz title ve text i belirtiyoruz
    
    
    const[ text, setText] = useState('');
    const[ title, setTitle] =useState('');
    const[ isSent, setIsSent]= useState(false);
    // is sent aslında şov ve ne yaptığımızı anlamaıza yardımcı oluyor
    //yoksa save den sonra title ve text i direkt boşluk olarak belirleememiz ve 
    //boxdaki yere de title ve text değerini atarsak sorun çözülmüş olur

    const savePost=() =>{
        fetch("/posts",
        {
            method:"POST",
            headers:{ 'Accept': 'application/json',
            'Content-Type': 'application/json'},
            body:JSON.stringify({
                title:title,
                text:text,
                userid:userid
            }),
        }).then((res) => res.json()).catch((err)=>console.log('error')) 
        //.then((res) => res.json()).then(data => console.log(data)).catch((err)=>console.log('error')) 
        
    }
    

    const handleSubmit= () => {
      //console.log(title, text);
      savePost();
      
      setIsSent(true);
      setTitle("");
      setText("");
      refreshPost();
    }

    const handleTitle= (value) =>{
        setTitle(value);
        setIsSent(false);
    }

    const handleText= (value) => {
        setText(value);
        setIsSent(false);
    }

    const onClose=()=>{
        setIsSent(false);}


    // Card sx için minWidth: 800,maxWidth: 800 şu şekilde min max da belirleebilr
    return ( 
        <div>
            <Snackbar open={isSent} autoHideDuration={3000} onClose={onClose}>
                <Alert onClose={onClose} severity="success" sx={{ width: '100%' }}>
                    Your post is successfully send!
                    </Alert>
            </Snackbar>

            <Card sx={{ width:1000 , 
                textAlign:'left' }}> 
                <CardHeader 
                    avatar={
                        <Link style={linkStyle} to={{pathname: '/users/' + userid}} > 
                            <Avatar  sx={{ bgcolor:"#e1bee7" }} aria-label="recipe">
                                {username.charAt(0).toUpperCase()}</Avatar>  
                        </Link>
                    }
       
                    title={ 
                        <OutlinedInput
                            id='outlined-adormen-amount'
                            multiline
                            placeholder="Title"
                            inputProps={{maxLength:25}}
                            fullWidth
                            value={title}
                            onChange={i => handleTitle( i.target.value)}></OutlinedInput> }/>
      
                <CardContent>
                    <Typography variant="body2" color="text.secondary">
                        <OutlinedInput
                            id='outlined-adormen-amount'
                            multiline
                            placeholder="Text"
                            inputProps={{maxLength:250}}
                            fullWidth
                            value={text} //boşken orada var olacak dğeeri belirliyo yani aslında yukarıda text belirledik ya "" şeklinde 
                            //biz bi şey yazmadığımızda bu şekilde görünecek eğer yazarsak da zaten onChange ile target.value alıyoruz ya ve 
                            //bunu text e atıyoeuz o şekilde görünecek  
                            onChange={i => handleText( i.target.value)}   
                            endAdornment={
                                <InputAdornment position="end">
                                <Button variant="contained"  style={pushButton }
                                    onClick={handleSubmit}>Post</Button>
                                </InputAdornment>}></OutlinedInput>
                    </Typography>
                </CardContent>
      
            </Card>
        </div>
    )
   
}

export default PostForm; //Dışarıdan post componenete erişmek isteyenler POst ismiyle çağırıp erişebilsin.

//Yukarıdaki return şu şekilde postlist.map ile postlist teki her öğeyi ayırıyoruz ve postlist.map() parantez içinde yazdığımız şeyleri
//her öğe iin ayrı ayrı yapıyor mesela aşağıda numbersı mapleyip her değerinin karesini alıp newArr atıyor ve bunu map sayesinde yapıyor.
//const numbers = [4, 9, 16, 25];
//const newArr = numbers.map(Math.sqrt)
//Biz ise maplediğimizde her bir elemana post dedik ve hepsi için post title ve text değerlerini aldık.


//En son çalışmasını görmek için index.js e app.js yerine ekledik.
