import React from "react";
import { Carousel } from "react-responsive-carousel";
import "react-responsive-carousel/lib/styles/carousel.min.css";

function HeroComponent() {
  return (
    <div className="w-full">
      <Carousel interval={2000}>
        <div>
          <img
            src="/images/carousel2.jpg"
            alt="Image 1"
            className="w-full h-full object-contain"
          />
        </div>
        <div>
          <img
            src="/images/carousel3.png"
            alt="Image 2"
            className="w-full h-full object-contain"
          />
        </div>
        <div>
          <img
            src="/images/carousel4.jpg"
            alt="Image 3"
            className="w-full h-full object-contain"
          />
        </div>
        <div>
          <img
            src="/images/carousel5.jpg"
            alt="Image 4"
            className="w-full h-full object-contain"
          />
        </div>
          </Carousel>
          <div className="m-8"></div>
    </div>
  );
}

export default HeroComponent;
