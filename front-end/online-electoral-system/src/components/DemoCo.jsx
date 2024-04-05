
import {useNavigate} from "react-router-dom"
const DemoComponent = () => {
  const navigate = useNavigate()

  function handleClick() {
    //alert("gets started...");
    navigate("/user/voting-interface");
  }
  return (
    <>
      <div className="py-10 md:py-20">
        <h2 className="text-2xl text-gray-700 text-center mb-8 font-serif">
          <i>Your vote, Your Rights</i>
        </h2>
        <div className="flex items-center justify-center h-screen">
          <button
            className="bg-violet-800 center text-4xl self-start px-12 py-6 rounded-md border-none transition hover:scale-105"
            onClick={handleClick}
          >
            Get's started... --move to voter login page
          </button>
        </div>
        <div className="flex flex-col md:flex-row px-2 md:px-8 gap-12">
          <div className="flex flex-col gap-4">
            <h4 className="uppercase text-sm md:text-lg transition-all hover:-skew-x-12">
              Hello world
            </h4>
            <p className="text-xs md:text-sm">
              Lorem ipsum dolor, sit amet consectetur adipisicing elit. Maiores
              voluptate omnis sint voluptas nesciunt, cumque quasi blanditiis,
              praesentium corrupti nobis laborum vitae. Exercitationem obcaecati
              quod esse recusandae dolorem, eligendi magnam!
            </p>
            <button
              className="bg-teal-500 uppercase text-xs self-start px-6 py-2 rounded-md border-none transition hover:scale-105"
              onClick={() => handleClick("Jonny")}
            >
              Send
            </button>
          </div>
          <div className="rounded-xl overflow-hidden hover:overflow-visible">
            <img
              src="/images/rose.jpg"
              alt=""
              className="w-full h-full transition-all hover:scale-105 hover:grayscale hover:blur-sm"
            />
          </div>
        </div>
      </div>
    </>
  );
};
export default DemoComponent;
