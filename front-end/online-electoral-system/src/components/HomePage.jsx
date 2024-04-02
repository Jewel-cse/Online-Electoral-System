import React from "react";
import HeaderComponent from "./Header";
import HeroComponent from "./Heros";
import FooterComponent from "./Footer";
import DemoComponent from "./DemoCo";

function HomeComponent() {
  
  return (
    <div className="px-2 py-1">
      <div>
        <HeaderComponent />
        <HeroComponent />
        <DemoComponent/>
        {/* <FooterComponent /> */}
      </div>
    </div>
  );
}

export default HomeComponent;
