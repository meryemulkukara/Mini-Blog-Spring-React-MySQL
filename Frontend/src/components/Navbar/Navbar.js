import React from "react";
import { Link } from "react-router-dom";
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import { purple } from '@mui/material/colors';




const linkStyle = {
  margin: "1rem",
  textDecoration: "none",
  color: 'white',
  boxShadow: 'none',
  
};




//Link te yapılan şey Home yazısını bir link haline getirip Home a tıkladığında link to / diyor ve biz app.js imizde / ın gideceği vomponentin
// home oldğuun tanımladığımız için Home componenetine gidiyor.
//İkinci link to için userid her userda değiştiğinden ve bu nedenle path de değişeceğinden userid şeklinde ekleme yapıyoruz ki yukarıda da 
//let ile userid taınmladık sonraısnda bunu otomatik çekip oraya ekleme ypacağız manuel bir sayı ranımlmaamyacğaız
function Navbar(){
    let userid=7
    
    return(
              
          <div>     
         
         <Box sx={{ flexGrow: 1}}  >
          
         <AppBar position="static"   >
           <Toolbar sx={{bgcolor:purple[200] , height:100 }}>
             <IconButton
               size="large"
               edge="start"
               color="inherit"
               aria-label="menu"
               sx={{ mr: 2 }}
             >
               <MenuIcon />
             </IconButton>
             <Typography variant="h6" component="div" sx={{ flexGrow: 1 }} textAlign={"left"}>
             <Link to='/'  style={linkStyle}>Home</Link>
             </Typography>
             <Typography variant="h6" component="div" >
             <Link to="/signin"  style={linkStyle}>Sing In</Link>
             </Typography>
             <Typography variant="h6" component="div" >
             /
             </Typography>
             <Typography variant="h6" component="div" >
             <Link to= "/signup"  style={linkStyle}>Sign Up</Link>
             </Typography>
             <Typography variant="h6" component="div" >
             <Link to={{pathname: '/users/' + userid}}  style={linkStyle}>User</Link>
             </Typography>
           </Toolbar>
         </AppBar>
       </Box>
       </div> 
    )
}

export default Navbar;