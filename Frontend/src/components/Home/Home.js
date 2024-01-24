import React from "react";
import Post from "../Post/Post";
//import Comment from "../Comment/Comment";
import { useState, useEffect } from "react";
import Container from '@mui/material/Container';
import PostForm from "../Post/PostForm";


const containerStyle= {
    display: 'flex',
    flexWrap:'wrap',
    flexDirection: 'row',
    justifyContent:'center',
    alignItems:'center',
    bgcolor: '#cfe8fc',
    height: '100vh',
    rowGap: 30,
    
    
  };

 

function Home(){




    const[error,setError]=useState(null); //Burada tanımladığımız şey bir errro variable gibi bi şey ve başlangıtça error değerini null tanımlıyoruz
    const[isLoaded, setIsLoaded] = useState(false); //Burada ise veri var yok durumunu tanımlıyoruz ve başlangıçta veri
    // olmayacağı için bunu false tanımlıyoruz
    const[postlist, setPostList]= useState([]); //Bütün postları dönmek başlangıçta boş bilr LİSTEDİR sonra gerekli liste döner


    const refreshPost=() =>{
        //fetch("http://localhost:8080/posts") //all post u çekebileceğimiz backend adresimiz 
        fetch("/posts")
        .then(res => res.json()) //çektikten sonra gelen response u res.json ile parse et
        .then(
            (result) => { //result geldiğinde bizim gelen datayı post listesine aktarmamız gerekmekte ve isloaded de true olmalı 
                setIsLoaded(true);
                setPostList(result); //post list gelen resulttur

            },
            (error) => { //error durumunda da isloaded true çünkü bi response var yani çalışıyor API call tamamlandı , seterroru da error tanımılıyoruz
                //yani bu durumlar aslında iki sonuç var ya result ve error resultsa list i result tanımla erorrsa error u erorr tanımla
                setIsLoaded(true);
                setError(error);

            }
        ) 
  }


    //API call u useEffect ile yapacğaız gerekli iskelet de şu
    useEffect(() => {
        refreshPost()
           }, [postlist])

    
        //Eğer error varsa en başta error null olduğu için girmez ama fetch sonrası eğer erorr varsa error set ettiysek 
        //bu if ile yapacğaımız şey error olursa ne döndüreceğimizdir 
        //yani aslında fetch ile biz isloading olsun, error olsun, postlist olsun onların API call sonrası değerlerini alıyoruz ve sonrasında da IF ile
        //bu değerler için vereceğmiiz responseları yani bizim frontendimizde görünecek olan sonuçları tanımlıyoruz 
    if(error){
        return <div> Error!!! </div>
    }
    else if( !isLoaded){
        return <div> Loading... </div>
    }
    else{
            //HTML lerin iççinde java script kodu yazmak istediğmizde {} kullanıyoruz aşağıda görüdlüğü gibi
            //map( post => ({post.title} {post.text} ) şeklinde yazdığımızda her post u liste gibi yazıyor ama amacımız her postu böyle tek post ve commentleri ile tutmalı 
            
        return (
            <div> 
            <Container fixed style={containerStyle}>
                    
                <PostForm  userid={2} username='marysu' refreshPost={refreshPost}></PostForm>
                    {postlist.map( post => (
                    <Post  userid={post.userid} username={post.username} title={post.title} text={post.text} postid={post.id} likes={post.postLikes} />
                    )
            )}</Container>
            </div>
            );
        }

   
}

export default Home;