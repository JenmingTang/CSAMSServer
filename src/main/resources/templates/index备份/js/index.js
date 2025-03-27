/*
* banner
* */
document.addEventListener('DOMContentLoaded', function() {
    const slideWrapper = document.querySelector('.ck-slide-wrapper');
    const slides = slideWrapper.querySelectorAll('li');
    const prev = document.querySelector('.ck-prev');
    const next = document.querySelector('.ck-next');
    const dots = document.querySelectorAll('.dot-wrap li');
    const slideWidth = slides[0].offsetWidth; // 每个li的宽度
    let currentSlide = 0;

    // 初始化dots的current类（可能已经设置好了）
    updateDots();

    function updateSlide() {
        slideWrapper.style.marginLeft = `-${currentSlide * slideWidth}px`;
        updateDots();
    }

    function updateDots() {
        dots.forEach((dot, index) => {
            dot.classList.toggle('current', index === currentSlide);
        });
    }

    prev.addEventListener('click', () => {
        currentSlide--;
        if (currentSlide < 0) currentSlide = slides.length - 1;
        updateSlide();
    });

    const nextSlide = ()=>{

        currentSlide++;
        if (currentSlide >= slides.length) currentSlide = 0;
        updateSlide();
    }
    next.addEventListener('click', () => {
        nextSlide()
    });

    dots.forEach((dot, index) => {
        dot.addEventListener('click', () => {
            currentSlide = index;
            updateSlide();
        });
    });

    // 初始调用
    updateSlide();
    setInterval(nextSlide,5000)
});
/*
* 通知通告滚动tang
* */
// 获取列表容器
const ul = document.getElementById('demo3');
console.log(ul)

// 定义移动函数
function moveFirstToLast() {
    // 获取所有 <li> 元素
    const lis = ul.children;

    if (lis.length === 0) return;

    // 获取第一个 <li> 元素
    const firstLi = lis[0];

    // 1. 先让元素淡出（过渡效果）
    firstLi.style.opacity = '0';

    // 2. 等待过渡结束后移动元素
    setTimeout(() => {
        // 将元素移动到末尾
        ul.appendChild(firstLi);

        // 3. 恢复元素的不透明度
        firstLi.style.opacity = '1';
    }, 500); // 与过渡时间（0.5秒）一致
}

// 自动触发（例如每5秒自动轮播）
setInterval(moveFirstToLast, 3000);
/*
* 专题专栏滚动tang
* <tr id="thematic-columns">
* */
// 获取列表容器
const ul2 = document.getElementById('thematic-columns');
console.log(ul2)

// 定义移动函数
function moveFirstToLast2() {
    // 获取所有 <li> 元素
    const lis = ul2.children;

    if (lis.length === 0) return;

    // 获取第一个 <li> 元素
    const firstLi = lis[0];

    // 1. 先让元素淡出（过渡效果）
    firstLi.style.opacity = '0';

    // 2. 等待过渡结束后移动元素
    setTimeout(() => {
        // 将元素移动到末尾
        ul2.appendChild(firstLi);

        // 3. 恢复元素的不透明度
        firstLi.style.opacity = '1';
    }, 500); // 与过渡时间（0.5秒）一致
}

// 自动触发（例如每5秒自动轮播）
setInterval(moveFirstToLast2, 3000);