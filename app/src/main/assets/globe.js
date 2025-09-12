const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera(45, window.innerWidth / window.innerHeight, 0.1, 1000);
const renderer = new THREE.WebGLRenderer({ antialias: true });
renderer.setSize(window.innerWidth, window.innerHeight);
document.body.appendChild(renderer.domElement);

const ambientLight = new THREE.AmbientLight(0xffffff, 0.6);
scene.add(ambientLight);

const directionalLight = new THREE.DirectionalLight(0xffffff, 0.8);
directionalLight.position.set(5, 3, 5);
scene.add(directionalLight);

const loader = new THREE.TextureLoader();
const dayURL = 'https://raw.githubusercontent.com/lieberschwager/LinguaFlowAI/main/app/src/main/assets/textures/earth_day_v2.jpg';

loader.load(dayURL, texture => {
  const earthGeometry = new THREE.SphereGeometry(2, 64, 64);
  const earthMaterial = new THREE.MeshStandardMaterial({ map: texture });
  const earthMesh = new THREE.Mesh(earthGeometry, earthMaterial);
  scene.add(earthMesh);

  camera.position.z = 5;

  function animate() {
    requestAnimationFrame(animate);
    earthMesh.rotation.y += 0.001;
    renderer.render(scene, camera);
  }
  animate();
});