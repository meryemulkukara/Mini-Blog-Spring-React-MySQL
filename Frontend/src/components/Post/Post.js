import React,{useState, useEffect, useRef} from "react";
//import { ReactDOM } from "react";
//import ReactDOM from "react-dom";
//import React,{useState} from "react";
import { styled } from '@mui/material/styles';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Collapse from '@mui/material/Collapse';
import Avatar from '@mui/material/Avatar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import FavoriteIcon from '@mui/icons-material/Favorite';
import CommentIcon from '@mui/icons-material/Comment';
import { Link } from "react-router-dom";
import { purple } from "@mui/material/colors";
import { Container } from "@mui/material";
import Comment from "../Comment/Comment";
import CommentForm from "../Comment/CommentForm";

const ExpandMore = styled((props) => {
    const { expand, ...other } = props;
    return <IconButton {...other} />;
  })(({ theme, expand }) => ({
    //transform: !expand ? 'rotate(0deg)' : 'rotate(180deg)',
    marginLeft: 'auto',
    transition: theme.transitions.create('transform', {
      duration: theme.transitions.duration.shortest,
    }),
  }));

const linkStyle={ textDecoration: 'none'}



function Post({userid,username, title, text, postid, likes}){ //<Post title={post.title} text={post.text} />  Home  belirttiğmiz title ve text i belirtiyoruz
    
  const [expanded, setExpanded] = React.useState(false);
  const[error,setError]=useState(null); //Burada tanımladığımız şey bir errro variable gibi bi şey ve başlangıtça error değerini null tanımlıyoruz
  const[isLoaded, setIsLoaded] = useState(false); //Burada ise veri var yok durumunu tanımlıyoruz ve başlangıçta veri
  // olmayacağı için bunu false tanımlıyoruz
  const[commentlist, setCommentList]= useState([]); //Bütün postları dönmek başlangıçta boş bilr LİSTEDİR sonra gerekli liste döner
  const isInitialMount= useRef(true);
  const [likeCount, setLikeCount]= useState(likes.length || 0);
  const[isLiked,setIsLiked]= useState(false);
  const [likeid, setLikeid]=useState(null);


    const handleExpandClick = () => {
      setExpanded(!expanded);
      refreshComments();
      //console.log(commentlist);
    };



    const handleLike= () => {

      setIsLiked(!isLiked);
      if(!isLiked){
        saveLike();
        setLikeCount(likeCount+1)
      }
      else {
        deleteLike();
        setLikeCount(likeCount-1);}
    }

    
    const refreshComments=() =>{
  
      fetch("/comments?postid"+postid)
      .then(res => res.json()) //çektikten sonra gelen response u res.json ile parse et
      .then(
      (result) => { //result geldiğinde bizim gelen datayı comment listesine aktarmamız gerekmekte ve isloaded de true olmalı 
          setIsLoaded(true);
          setCommentList(result); //comment list gelen resulttur
          //console.log(commentlist);

      },
      (error) => { //error durumunda da isloaded true çünkü bi response var yani çalışıyor API call tamamlandı , seterroru da error tanımılıyoruz
          //yani bu durumlar aslında iki sonuç var ya result ve error resultsa list i result tanımla erorrsa error u erorr tanımla
          setIsLoaded(true);
          setError(error);

      }
      ) 
    }

    const saveLike=() =>{
      fetch("/likes",
      {
          method:"POST",
          headers:{ 'Accept': 'application/json',
          'Content-Type': 'application/json'},
          body:JSON.stringify({
              postid:postid,
              userid:userid
          }),
      }).then((res) => res.json()).catch((err)=>console.log('error')) 
      //.then((res) => res.json()).then(data => console.log(data)).catch((err)=>console.log('error')) 
      
  }

  const deleteLike=() =>{
    fetch( "/likes/"+likeid,
    {
      method: "DELETE",
      }).then((res)=>res.json()).catch((err)=> console.log('error'))

  }
  



    

    //Normalde useEffeck sayfa giriş yaptığı anda çalışır biz de bunun içn biz bi şeye tıkladığımızda çalışsın istedik 
    //ama commente tıkladığımzıı nasıl nalayacak bilmiyom
    useEffect(() => { 
     
      //if(isInitialMount.current)
       // isInitialMount.current=false;
      //else   refreshComments();
         }, [commentlist]);

    useEffect(() =>{ checkLikes()}, []);

        
    const checkLikes= () =>{ 
      var likeControl= likes.find( like => (like.userid)===userid);
      if( likeControl!= null)
      {  setLikeid(likeControl.id);
        setIsLiked(true);}
    }

    // Card sx için minWidth: 800,maxWidth: 800 şu şekilde min max da belirleebilr
    return ( 
        <div>
        <Card sx={{ width:1000 , 
        textAlign:'left' }}> 
      <CardHeader 
        avatar={
         <Link style={linkStyle} to={{pathname: '/users/' + userid}} > 

          <Avatar  sx={{ bgcolor: "#e1bee7" }} aria-label="recipe">
            {username.charAt(0).toUpperCase()}   
          </Avatar>  
            
        </Link>
          
        }
       
        title={title } 
      />
      
      <CardContent>
        <Typography variant="body2" color="text.secondary">
          {text} {postid}
        </Typography>
      </CardContent>
      <CardActions disableSpacing>
        <IconButton onClick={handleLike}
        aria-label="add to favorites">
          <FavoriteIcon style={isLiked ? {color:purple[500]}: null}/>  
        </IconButton>
        {likeCount}
        <ExpandMore
          expand={expanded}
          onClick={handleExpandClick}
          aria-expanded={expanded}
          aria-label="show more"
        >
          <CommentIcon />
        </ExpandMore>
      </CardActions>
      <Collapse in={expanded} timeout="auto" unmountOnExit>
        <Container fixed>
          {error?"error":
            isLoaded? commentlist.map(comment => ( <Comment userid={comment.userid} username={comment.username} text={comment.text} />))
             :"loading"}
           
        </Container >
         <CommentForm userid={1} username='bahrisu' postid={1} refreshComments={refreshComments} />
      </Collapse>
    </Card>
    </div>
    )
   
}

export default Post; //Dışarıdan post componenete erişmek isteyenler POst ismiyle çağırıp erişebilsin.

//Yukarıdaki return şu şekilde postlist.map ile postlist teki her öğeyi ayırıyoruz ve postlist.map() parantez içinde yazdığımız şeyleri
//her öğe iin ayrı ayrı yapıyor mesela aşağıda numbersı mapleyip her değerinin karesini alıp newArr atıyor ve bunu map sayesinde yapıyor.
//const numbers = [4, 9, 16, 25];
//const newArr = numbers.map(Math.sqrt)
//Biz ise maplediğimizde her bir elemana post dedik ve hepsi için post title ve text değerlerini aldık.


//En son çalışmasını görmek için index.js e app.js yerine ekledik.
