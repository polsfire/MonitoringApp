package com.bfi.ecm.DTO;

public record ErrorDto(String message){}

/*
the record is same as classe we can say it just simply the creation of classe so it contaitns
: thevariable declaration which are private + final
+ all the getters and setters and also all the constrcutor (the canaloqual constructor)
        +methode equals
        +emthode to string name+value ==just pass the object in the println automatically call the to string function
the get fucntion name is hte name of hte varible only
i m allowed to ovveridde the already created fucntion
they are immutable that s prove why therei s no setters
i m elligable to ceraete function
        create statix methode
create static field : public static final string DEFAUT-EMPLOYE_NAME="George";
instance field are not allowed they need to be definied up
cannot extends from other classe
they are implicitly final which means that they cannot be extended by other classe aso
i am allowed to implement interface
i can ovveride the constructor but i have to keep the same signature
 bonus with record for the construcor:i can use compact cosntrcutor to ovveride the conocal constrcuot
        same as the old cosntrcutor bad need to remove the parameters and also the set because already ecxiste
        also i can build other constrcutor with deffernect parameter
*/