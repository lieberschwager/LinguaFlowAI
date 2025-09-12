const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera(
  45,
  window.innerWidth / window.innerHeight,
  0.1,
  1000
);
camera.position.set(0, 0, 6);
camera.lookAt(scene.position);

const renderer = new THREE.WebGLRenderer({ antialias: true });
renderer.setSize(window.innerWidth, window.innerHeight);
document.body.style.margin = "0";
document.body.style.overflow = "hidden";
document.body.appendChild(renderer.domElement);

// Lichtquellen
const ambientLight = new THREE.AmbientLight(0xffffff, 0.6);
scene.add(ambientLight);

const directionalLight = new THREE.DirectionalLight(0xffffff, 0.8);
directionalLight.position.set(5, 3, 5);
scene.add(directionalLight);

// Textur-Loader
const loader = new THREE.TextureLoader();

// GitHub Raw-Links
const dayURL = "https://raw.githubusercontent.com/lieberschwager/LinguaFlowAI/main/app/src/main/assets/textures/earth_day_v2.jpg";
const nightURL = "https://raw.githubusercontent.com/lieberschwager/LinguaFlowAI/main/app/src/main/assets/textures/earth_night_v2.jpg";
const cloudURL = "https://raw.githubusercontent.com/lieberschwager/LinguaFlowAI/main/app/src/main/assets/textures/earth_clouds_v2.jpg";
const specularURL = "https://raw.githubusercontent.com/lieberschwager/LinguaFlowAI/main/app/src/main/assets/textures/earth_specular_v2.png";

// Texturen laden und kombinieren
loader.load(dayURL, (dayTexture) => {
  loader.load(nightURL, (nightTexture) => {
    loader.load(specularURL, (specularTexture) => {
      loader.load(cloudURL, (cloudTexture) => {
        // Erde mit Tag/Nacht/Glanz
        const earthGeometry = new THREE.SphereGeometry(1.2, 48, 48);
        const earthMaterial = new THREE.MeshPhongMaterial({
          map: dayTexture,
          emissiveMap: nightTexture,
          emissive: new THREE.Color(0x222222),
          specularMap: specularTexture,
          specular: new THREE.Color("grey"),
          shininess: 10
        });
        const earthMesh = new THREE.Mesh(earthGeometry, earthMaterial);
        scene.add(earthMesh);

        // Wolkenlayer
        const cloudGeometry = new THREE.SphereGeometry(1.22, 48, 48);
        const cloudMaterial = new THREE.MeshLambertMaterial({
          map: cloudTexture,
          transparent: true
        });
        const cloudMesh = new THREE.Mesh(cloudGeometry, cloudMaterial);
        scene.add(cloudMesh);

        // Animation
        function animate() {
          requestAnimationFrame(animate);
          earthMesh.rotation.y += 0.001;
          cloudMesh.rotation.y += 0.0005;
          renderer.render(scene, camera);
        }
        animate();
      });
    });
  });
});

// Responsives Verhalten
window.addEventListener("resize", () => {
  camera.aspect = window.innerWidth / window.innerHeight;
  camera.updateProjectionMatrix();
  renderer.setSize(window.innerWidth, window.innerHeight);
});