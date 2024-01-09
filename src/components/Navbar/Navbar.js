import React from "react";
import { Link } from "react-router-dom";


//Link te yapılan şey Home yazısını bir link haline getirip Home a tıkladığında link to / diyor ve biz app.js imizde / ın gideceği vomponentin
// home oldğuun tanımladığımız için Home componenetine gidiyor.
//İkinci link to için userid her userda değiştiğinden ve bu nedenle path de değişeceğinden userid şeklinde ekleme yapıyoruz ki yukarıda da 
//let ile userid taınmladık sonraısnda bunu otomatik çekip oraya ekleme ypacağız manuel bir sayı ranımlmaamyacğaız
function Navbar(){
    let userid=7
    return(
        <div>
            <ul> 
                <li><Link to='/'>Home</Link></li>
                <li><Link to={{pathname: '/users/' + userid}}>User</Link></li>
            </ul>
        </div>
    )
}

export default Navbar;