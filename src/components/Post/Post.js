import React,{useState, useEffect} from "react";
//import { ReactDOM } from "react";
import ReactDOM from "react-dom";

function Post(){

    const[error,setError]=useState(null); //Burada tanımladığımız şey bir errro variable gibi bi şey ve başlangıtça error değerini null tanımlıyoruz
    const[isLoaded, setIsLoaded] = useState(false); //Burada ise veri var yok durumunu tanımlıyoruz ve başlangıçta veri
    // olmayacağı için bunu false tanımlıyoruz
    const[postlist, setPostList]= useState([]); //Bütün postları dönmek başlangıçta boş bilr LİSTEDİR sonra gerekli liste döner


    //API call u useEffect ile yapacğaız gerekli iskelet de şu
    useEffect(() => {
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
        )    }, [])

    
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
            return (
                <ul> 
                    {postlist.map( post => (
                        <li>
                            {post.title} {post.text}
                        </li>
                    )

                    )}
                </ul>
            );
        }

}

export default Post; //Dışarıdan post componenete erişmek isteyenler POst ismiyle çağırıp erişebilsin.

//Yukarıdaki return şu şekilde postlist.map ile postlist teki her öğeyi ayırıyoruz ve postlist.map() parantez içinde yazdığımız şeyleri
//her öğe iin ayrı ayrı yapıyor mesela aşağıda numbersı mapleyip her değerinin karesini alıp newArr atıyor ve bunu map sayesinde yapıyor.
//const numbers = [4, 9, 16, 25];
//const newArr = numbers.map(Math.sqrt)
//Biz ise maplediğimizde her bir elemana post dedik ve hepsi için post title ve text değerlerini aldık.



//En son çalışmasını görmek için index.js e app.js yerine ekledik.